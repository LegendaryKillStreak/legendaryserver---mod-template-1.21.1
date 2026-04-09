package net.lksls.legendaryserver.screen.custom;

import net.lksls.legendaryserver.block.entity.custom.CustomSpawnerBlockEntity;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.screen.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class CustomSpawnerScreenHandler extends ScreenHandler {

    private final CustomSpawnerBlockEntity blockEntity;
    private final PlayerInventory playerInventory;

    // delegate indices
    private static final int PROP_STACK = 0;
    private static final int PROP_LEVEL = 1;
    private static final int PROP_XP_SCALED = 2;
    private static final int PROP_MAX_XP_SCALED = 3;
    private static final int PROP_XP_LOW = 4;
    private static final int PROP_XP_HIGH = 5;

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();

        long xp = blockEntity.getXpStored();
        long max = CustomSpawnerBlockEntity.MAX_XP;

        delegate.set(PROP_STACK, blockEntity.getStackCount());
        delegate.set(PROP_LEVEL, blockEntity.getLevel());

        int scaled = (int)((xp / (float)max) * 1000f);
        scaled = Math.min(1000, Math.max(0, scaled));
        delegate.set(PROP_XP_SCALED, scaled);
        delegate.set(PROP_MAX_XP_SCALED, 1000);



        delegate.set(PROP_XP_LOW,  (int)(xp & 0xFFFFFFFFL));
        delegate.set(PROP_XP_HIGH, (int)((xp >>> 32) & 0xFFFFFFFFL));
    }






    private final PropertyDelegate delegate;
    public long getXpRaw() {
        long low = delegate.get(PROP_XP_LOW) & 0xFFFFFFFFL;
        long high = delegate.get(PROP_XP_HIGH) & 0xFFFFFFFFL;
        return (high << 32) | low;
    }


    public static final int SLOTS_PER_PAGE = 54;

    public CustomSpawnerScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModScreenHandlers.CUSTOM_SPAWNER_SCREEN_HANDLER, syncId);

        this.playerInventory = playerInventory;
        this.blockEntity = (CustomSpawnerBlockEntity) playerInventory.player.getWorld().getBlockEntity(pos);

        // one delegate, 4 values: stack, level, xp, maxXp
        this.delegate = new ArrayPropertyDelegate(6);
        addProperties(this.delegate);

        rebuildSlots();
    }


    private void rebuildSlots() {
        slots.clear();
        addCoreSlot();
        addStorageSlots();
        addPlayerInventory(playerInventory);
    }

    // -----------------------------
    // SLOT POSITIONING
    // -----------------------------

    private static final int CORE_X = 177;
    private static final int CORE_Y = 24;

    private static final int STORAGE_START_X = 8;
    private static final int STORAGE_START_Y = 24;

    private static final int PLAYER_INV_X = 8;
    private static final int PLAYER_INV_Y = 140;
    private static final int HOTBAR_Y = PLAYER_INV_Y + 58;

    // -----------------------------
    // BUTTON HANDLING
    // -----------------------------
    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == 0) {
            if (blockEntity != null) {
                blockEntity.releaseAllXp(player);
            }
            return true;
        }
        return false;
    }

    private void addCoreSlot() {
        this.addSlot(new Slot(blockEntity.getCore(), 0, CORE_X, CORE_Y) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return ModItems.CORE_ITEMS.contains(stack.getItem());
            }
        });
    }

    private void addStorageSlots() {
        SimpleInventory inv = blockEntity.getStorage();
        int visible = Math.min(inv.size(), SLOTS_PER_PAGE);

        for (int i = 0; i < visible; i++) {
            int x = STORAGE_START_X + (i % 9) * 18;
            int y = STORAGE_START_Y + (i / 9) * 18;

            addSlot(new Slot(inv, i, x, y) {
                @Override
                public boolean canInsert(ItemStack stack) {
                    for (ItemStack allowed : blockEntity.getGeneratedItems()) {
                        if (ItemStack.areItemsAndComponentsEqual(stack, allowed)) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    private void addPlayerInventory(PlayerInventory inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, 9 + row * 9 + col,
                        PLAYER_INV_X + col * 18,
                        PLAYER_INV_Y + row * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inv, i, PLAYER_INV_X + i * 18, HOTBAR_Y));
        }
    }





    public PropertyDelegate getDelegate() {
        return delegate;
    }

    public float getXpPercent() {
        return delegate.get(PROP_XP_SCALED) / 1000f;
    }


    public long getMaxXp() {
        return delegate.get(PROP_MAX_XP_SCALED);
    }


    public int getStackCount() { return delegate.get(PROP_STACK); }
    public int getLevel() { return delegate.get(PROP_LEVEL); }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (!slot.hasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack original = slot.getStack();
        newStack = original.copy();

        int coreSlot = 0;
        int storageStart = 1;
        int storageEnd = storageStart + SLOTS_PER_PAGE;
        int playerStart = storageEnd;
        int playerEnd = playerStart + 36;

        if (index >= storageStart && index < storageEnd) {
            if (!this.insertItem(original, playerStart, playerEnd, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index >= playerStart && index < playerEnd) {
            boolean allowed = false;
            for (ItemStack allowedDrop : blockEntity.getGeneratedItems()) {
                if (ItemStack.areItemsAndComponentsEqual(original, allowedDrop)) {
                    allowed = true;
                    break;
                }
            }

            if (!allowed) {
                return ItemStack.EMPTY;
            }

            if (!this.insertItem(original, storageStart, storageEnd, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index == coreSlot) {
            if (!this.insertItem(original, playerStart, playerEnd, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (original.isEmpty()) {
            slot.setStack(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public CustomSpawnerBlockEntity getBlockEntity() {
        return blockEntity;
    }
}






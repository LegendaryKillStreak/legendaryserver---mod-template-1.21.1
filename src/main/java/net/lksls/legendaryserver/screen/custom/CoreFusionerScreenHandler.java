package net.lksls.legendaryserver.screen.custom;

import net.lksls.legendaryserver.block.entity.custom.CoreFusionerBlockEntity;
import net.lksls.legendaryserver.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class CoreFusionerScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate delegate;
    public final CoreFusionerBlockEntity blockEntity;

    public CoreFusionerScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory,
                playerInventory.player.getWorld().getBlockEntity(pos),
                ((CoreFusionerBlockEntity) playerInventory.player.getWorld().getBlockEntity(pos)).getPropertyDelegate()
        );
    }


    public CoreFusionerScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate delegate) {
        super(ModScreenHandlers.CORE_FUSIONER_SCREEN_HANDLER, syncId);

        this.blockEntity = (CoreFusionerBlockEntity) blockEntity;
        this.inventory = this.blockEntity;
        this.delegate = delegate;

        checkSize(inventory, 3);

        // Input slots
        this.addSlot(new Slot(inventory, 0, 44, 26));
        this.addSlot(new Slot(inventory, 1, 44, 50));

        // Output slot
        this.addSlot(new Slot(inventory, 2, 116, 38) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);
    }

    public boolean isCrafting() {
        return delegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = delegate.get(0);
        int max = delegate.get(1);
        int pixelWidth = 24;

        return max != 0 && progress != 0 ? (progress * pixelWidth) / max : 0;
    }

    public PropertyDelegate getDelegate() {
        return delegate;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasStack()) {
            ItemStack original = slot.getStack();
            newStack = original.copy();

            if (slotIndex < inventory.size()) {
                if (!insertItem(original, inventory.size(), slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!insertItem(original, 0, inventory.size(), false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9,
                        8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i,
                    8 + i * 18, 142));
        }
    }
}



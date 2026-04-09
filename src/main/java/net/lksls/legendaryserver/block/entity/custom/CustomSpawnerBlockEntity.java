package net.lksls.legendaryserver.block.entity.custom;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.screen.custom.CustomSpawnerScreenHandler;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.PacketByteBuf;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * CustomSpawnerBlockEntity
 *
 * - Uses vanilla BE update packets (writeNbt/readNbt + world.updateListeners)
 * - Spawns ExperienceOrbEntity on release so Mending works
 * - Provides client setter setXpStoredClient(...) (no markDirty on client)
 * - Lightweight logging for server-side events
 */
public class CustomSpawnerBlockEntity extends BlockEntity implements SidedInventory {


    @Override
    public int[] getAvailableSlots(Direction side) {
        // expose ONLY storage slots (0–53)
        int size = storage.size();
        int[] slots = new int[size];
        for (int i = 0; i < size; i++) slots[i] = i;
        return slots;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        // spawner does NOT accept hopper insertion
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        // allow extraction ONLY from storage slots
        return slot < storage.size();
    }




    private static final Logger LOGGER = Logger.getLogger("legendaryserver.CustomSpawnerBE");

    // SINGLE PAGE storage: exactly 54 slots
    private final SimpleInventory storage = new SimpleInventory(54);
    private final SimpleInventory core = new SimpleInventory(1);

    private int stackCount = 1;
    private int level = 1;
    private long xpStored = 0L; // server-side XP stored as long

    public static final int MAX_STACK = 256;
    public static final long MAX_XP = 500_000L;

    public CustomSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CUSTOM_SPAWNER_BE, pos, state);
    }
    // imports needed at top of file:
//





    private static final Map<Item, Map<Integer, List<ItemStack>>> LEVEL_LOOT = Map.of(
            ModItems.DARK_SOUL_CORE, Map.of(
                    1, List.of(new ItemStack(Items.SOUL_SAND, 1), new ItemStack(Items.OBSIDIAN, 1)),
                    5, List.of(new ItemStack(ModItems.FUSED_SILICA_POWDER, 2), new ItemStack(Items.OBSIDIAN, 1)),
                    10, List.of(new ItemStack(ModItems.FUSED_SILICA_POWDER, 3), new ItemStack(Items.OBSIDIAN, 2))
            ),
            ModItems.LIGHT_CORE, Map.of(
                    1, List.of(new ItemStack(ModItems.FUSED_SILICA_POWDER, 1), new ItemStack(Items.OBSIDIAN, 1)),
                    5, List.of(new ItemStack(ModItems.FUSED_SILICA_POWDER, 2), new ItemStack(Items.OBSIDIAN, 1)),
                    10, List.of(new ItemStack(ModItems.FUSED_SILICA_POWDER, 3), new ItemStack(Items.OBSIDIAN, 2))
            )
    );

    // --- Vanilla BE update helpers (no @Override to be mapping-robust) ---

    public net.minecraft.nbt.NbtCompound toInitialChunkDataNbt() {
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        writeNbt(nbt, null);

        return nbt;
    }

    public net.minecraft.nbt.NbtCompound toUpdateTag() {
        net.minecraft.nbt.NbtCompound nbt = new net.minecraft.nbt.NbtCompound();
        writeNbt(nbt, null);

        return nbt;
    }

    // ---------------- GETTERS ----------------

    public SimpleInventory getStorage() { return storage; }
    public SimpleInventory getCore() { return core; }
    public int getStackCount() { return stackCount; }
    public int getLevel() { return level; }
    public long getXpStored() { return xpStored; }

    // ---------------- LOGIC ----------------

    public void addSpawnerToStack(int amount) {
        stackCount = Math.min(MAX_STACK, stackCount + amount);
        markDirty();
    }

    // client-only setter called by optional client receiver; harmless to keep
    public void setXpStoredClient(long xp) {
        this.xpStored = xp;
        // Do NOT call markDirty() on client
    }

    // Clamped addXp: never exceed MAX_XP
    public void addXp(long amount) {
        if (xpStored >= MAX_XP) return;
        long before = xpStored;
        xpStored = Math.min(MAX_XP, xpStored + amount);
        markDirty();



        if (world != null && !world.isClient) {
            // force vanilla to send BE update packet to clients
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);

        }
    }

    private List<ItemStack> getLevelLoot(Item coreItem, int level) {
        Map<Integer, List<ItemStack>> lootByLevel = LEVEL_LOOT.get(coreItem);
        if (lootByLevel == null) return List.of();

        int bestLevel = 1;
        for (int definedLevel : lootByLevel.keySet()) {
            if (definedLevel <= level && definedLevel > bestLevel) bestLevel = definedLevel;
        }

        return lootByLevel.get(bestLevel).stream().map(ItemStack::copy).toList();
    }

    public List<ItemStack> getGeneratedItems() {
        ItemStack coreItem = core.getStack(0);
        if (coreItem.isEmpty()) return List.of();
        return getLevelLoot(coreItem.getItem(), level);
    }

    public void sync() {
        if (world != null && !world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    public void syncToClient() {
        if (world != null && !world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }



    // ---------------- TICK ----------------

    public static void tick(World world, BlockPos pos, BlockState state, CustomSpawnerBlockEntity be) {
        if (world.isClient) return;

        if (world.getTime() % 20 == 0) {
            be.addXp(be.getStackCount());
        }

// Only sync if a player has the GUI open
        if (be.world.getPlayers().stream().anyMatch(p -> p.currentScreenHandler instanceof CustomSpawnerScreenHandler)) {
            be.syncToClient();
        }



        int stack = be.getStackCount();
        int delay = Math.max(5, 40 - stack);

        if (world.getTime() % delay != 0) return;

        List<ItemStack> drops = be.getGeneratedItems();
        for (ItemStack drop : drops) {
            be.insertIntoStorage(drop);
        }
    }







    private void insertIntoStorage(ItemStack drop) {
        SimpleInventory inv = this.getStorage();

        for (int i = 0; i < inv.size(); i++) {
            ItemStack slot = inv.getStack(i);

            if (slot.isEmpty()) {
                inv.setStack(i, drop.copy());
                inv.markDirty();
                this.markDirty();
                return;
            }

            if (ItemStack.areItemsAndComponentsEqual(slot, drop) && slot.getCount() < slot.getMaxCount()) {
                slot.increment(1);
                inv.markDirty();
                this.markDirty();
                return;
            }
        }
    }

    // kept for compatibility; not used by tick() but harmless
    private static final long XP_PER_TICK = 1L;

    public void addXpTick() {
        if (xpStored < MAX_XP) {
            xpStored = Math.min(MAX_XP, xpStored + XP_PER_TICK * stackCount);
            markDirty();
            if (world != null && !world.isClient) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
    }
    


    // Release all XP to player (server-side) by spawning orbs so Mending works
    public void releaseAllXp(PlayerEntity player) {
        if (xpStored <= 0) {
            LOGGER.fine(() -> "releaseAllXp called but xpStored==0 at " + pos);
            return;
        }

        long xpToGiveLong = xpStored;
        xpStored = 0;
        markDirty();



        if (world != null && !world.isClient) {
            // spawn ExperienceOrbEntity(s) so mending and pickup logic runs naturally
            int remaining = (int) Math.min(Integer.MAX_VALUE, xpToGiveLong);

            // split into orb sizes (use 247 as a safe upper chunk; adjust if you prefer)
            while (remaining > 0) {
                int orbValue = Math.min(remaining, 247);
                ExperienceOrbEntity orb = new ExperienceOrbEntity(world, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, orbValue);
                world.spawnEntity(orb);
                remaining -= orbValue;
            }

            // notify clients so the GUI updates (vanilla BE update packet)
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);

        }
    }

    // ---------------- NBT ----------------

    @Override
    protected void writeNbt(NbtCompound nbt, net.minecraft.registry.RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        nbt.putInt("StackCount", stackCount);
        nbt.putInt("Level", level);
        nbt.putLong("XpStored", xpStored);

        DefaultedList<ItemStack> storageList = DefaultedList.ofSize(storage.size(), ItemStack.EMPTY);
        for (int i = 0; i < storage.size(); i++) storageList.set(i, storage.getStack(i));
        NbtCompound storageTag = new NbtCompound();
        Inventories.writeNbt(storageTag, storageList, registryLookup);
        nbt.put("Storage", storageTag);

        DefaultedList<ItemStack> coreList = DefaultedList.ofSize(core.size(), ItemStack.EMPTY);
        coreList.set(0, core.getStack(0));
        NbtCompound coreTag = new NbtCompound();
        Inventories.writeNbt(coreTag, coreList, registryLookup);
        nbt.put("Core", coreTag);
    }

    @Override
    public void readNbt(NbtCompound nbt, net.minecraft.registry.RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        stackCount = nbt.getInt("StackCount");
        level = nbt.getInt("Level");
        xpStored = nbt.getLong("XpStored");

        DefaultedList<ItemStack> storageList = DefaultedList.ofSize(storage.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt.getCompound("Storage"), storageList, registryLookup);
        for (int i = 0; i < storage.size(); i++) storage.setStack(i, storageList.get(i));

        DefaultedList<ItemStack> coreList = DefaultedList.ofSize(core.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt.getCompound("Core"), coreList, registryLookup);
        core.setStack(0, coreList.get(0));
    }


    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return storage.getStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = storage.removeStack(slot, amount);
        markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = storage.removeStack(slot);
        markDirty();
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        storage.setStack(slot, stack);
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {
        storage.clear();
        markDirty();
    }

}






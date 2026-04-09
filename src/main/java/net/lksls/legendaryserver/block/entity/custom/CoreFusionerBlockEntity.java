package net.lksls.legendaryserver.block.entity.custom;

import net.lksls.legendaryserver.block.custom.CoreFusionerBlock;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoreFusionerBlockEntity extends BlockEntity implements Inventory {

    // 0,1 = inputs, 2 = output
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private int progress = 0;
    private int maxProgress = 0;
    private boolean lit = false;

    // 🔥 ADD THIS: property delegate for GUI syncing
    private final PropertyDelegate propertyDelegate = new ArrayPropertyDelegate(2);
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    // simple recipe map: (inputA,inputB) -> (output, time)
    private static final Map<List<Item>, FusionRecipe> RECIPES = new HashMap<>();

    static {
        // example recipes
        RECIPES.put(List.of(Items.DIAMOND, Items.NETHER_STAR),
                new FusionRecipe(new ItemStack(Items.END_CRYSTAL), 200));
        RECIPES.put(List.of(ModItems.LIGHT_CORE, ModItems.DARK_CORE),
                new FusionRecipe(new ItemStack(ModItems.SHADOW_CORE), 3000));
        RECIPES.put(List.of(ModItems.DARK_CORE, ModItems.SHADOW_CORE),
                new FusionRecipe(new ItemStack(ModItems.END_CORE), 5500));
        RECIPES.put(List.of(ModItems.DARK_SOUL_CORE, ModItems.SKELETON_CORE),
                new FusionRecipe(new ItemStack(ModItems.WITHER_SKELETON_CORE), 2000));
    }

    public CoreFusionerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CORE_FUSIONER_BE, pos, state);
    }


    // ---------- Inventory ----------

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(inventory, slot, amount);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(inventory, slot);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }

    // ---------- Tick logic ----------

    public static void tick(World world, BlockPos pos, BlockState state, CoreFusionerBlockEntity be) {
        if (world.isClient) {
            if (be.lit) {
                spawnParticles(world, pos);
            }
            return;
        }

        boolean wasLit = be.lit;

        if (be.hasRecipe()) {
            if (be.progress == 0) {
                // start: set time, sound, lit
                be.maxProgress = be.getRecipe().time();
                be.setLit(true);
                world.playSound(
                        null,
                        pos,
                        SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE,
                        SoundCategory.BLOCKS,
                        1.0f,
                        -5.0f
                );
            }

            be.progress++;

            if (be.progress >= be.maxProgress) {
                be.craftItem();
                be.progress = 0;
                be.maxProgress = 0;
                be.setLit(false);
            }
        } else {
            be.progress = 0;
            be.maxProgress = 0;
            be.setLit(false);
        }

        if (wasLit != be.lit) {
            world.setBlockState(pos, state.with(CoreFusionerBlock.LIT, be.lit), Block.NOTIFY_ALL);
        }

        // sync progress to GUI
        be.propertyDelegate.set(0, be.progress);
        be.propertyDelegate.set(1, be.maxProgress);

        if (wasLit != be.lit) {
            world.setBlockState(pos, state.with(CoreFusionerBlock.LIT, be.lit), Block.NOTIFY_ALL);
        }
    }


    private static void spawnParticles(World world, BlockPos pos) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.0;
        double z = pos.getZ() + 0.5;

        world.addParticle(
                ParticleTypes.END_ROD,
                x + (world.random.nextDouble() - 0.5) * 0.3,
                y,
                z + (world.random.nextDouble() - 0.5) * 0.3,
                0.0,
                0.05 + world.random.nextDouble() * 0.05,
                0.0
        );
    }

    private boolean hasRecipe() {
        FusionRecipe recipe = getRecipe();
        if (recipe == null) return false;

        ItemStack outputSlot = inventory.get(2);
        ItemStack result = recipe.output();

        if (outputSlot.isEmpty()) return true;
        if (!ItemStack.areItemsEqual(outputSlot, result)) return false;
        return outputSlot.getCount() + result.getCount() <= outputSlot.getMaxCount();
    }

    @Nullable
    private FusionRecipe getRecipe() {
        ItemStack a = inventory.get(0);
        ItemStack b = inventory.get(1);
        if (a.isEmpty() || b.isEmpty()) return null;

        List<Item> key1 = List.of(a.getItem(), b.getItem());
        List<Item> key2 = List.of(b.getItem(), a.getItem()); // order-insensitive

        FusionRecipe recipe = RECIPES.get(key1);
        if (recipe == null) recipe = RECIPES.get(key2);
        return recipe;
    }

    private void craftItem() {
        FusionRecipe recipe = getRecipe();
        if (recipe == null) return;

        ItemStack result = recipe.output().copy();

        ItemStack output = inventory.get(2);
        if (output.isEmpty()) {
            inventory.set(2, result);
        } else {
            output.increment(result.getCount());
        }

        inventory.get(0).decrement(1);
        inventory.get(1).decrement(1);

        markDirty();
    }

    private void setLit(boolean lit) {
        this.lit = lit;
        markDirty();
    }

    // ---------- NBT ----------

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("Progress", progress);
        nbt.putInt("MaxProgress", maxProgress);
        nbt.putBoolean("Lit", lit);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("Progress");
        maxProgress = nbt.getInt("MaxProgress");
        lit = nbt.getBoolean("Lit");
    }

    // ---------- Recipe record ----------

    private record FusionRecipe(ItemStack output, int time) {}
}


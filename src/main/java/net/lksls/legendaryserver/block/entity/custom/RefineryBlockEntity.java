package net.lksls.legendaryserver.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.lksls.legendaryserver.block.custom.RefineryBlock;
import net.lksls.legendaryserver.block.entity.ImplementedInventory;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.recipe.ModRecipes;
import net.lksls.legendaryserver.recipe.RefineryRecipe;
import net.lksls.legendaryserver.recipe.RefineryRecipeInput;
import net.lksls.legendaryserver.screen.custom.RefineryScreen;
import net.lksls.legendaryserver.screen.custom.RefineryScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class RefineryBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4,ItemStack.EMPTY);

    private static final int FLUID_ITEM_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int ENERGY_ITEM_SLOT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;
    private int liquidRemaining = 0;
    private int liquidMax = 500;

    private int energyRemaining = 0;
    private int energyMax = 50000;
    private enum EnergyRefillRule {
        MISSING_AT_LEAST_VALUE,
        ONLY_WHEN_EMPTY
    }
    private enum LiquidRefillRule {
        MISSING_AT_LEAST_VALUE,
        ONLY_WHEN_EMPTY
    }


    private static final Map<Item, Integer> ENERGY_VALUES = Map.of(
            ModItems.NICKEL_ZINC_BATTERY, 150,
            ModItems.XENTHRITE_ROD, 50000//,
           // ModItems.REFINED_XENTHRITE, 300,
            //ModItems.XENTHRITE_ENERGY_ROD, 50000
    );

    private static final Map<Item, EnergyRefillRule> ENERGY_RULES = Map.of(
            ModItems.NICKEL_ZINC_BATTERY, EnergyRefillRule.MISSING_AT_LEAST_VALUE,
            ModItems.XENTHRITE_ROD, EnergyRefillRule.ONLY_WHEN_EMPTY
            //ModItems.LITHIUM_ION_BATTERY, EnergyRefillRule.MISSING_AT_LEAST_VALUE,
            //ModItems.XENTHRITE_ENERGY_ROD, EnergyRefillRule.ONLY_WHEN_EMPTY
    );

    private static final Map<Item, Integer> LIQUID_VALUES = Map.of(
            Items.WATER_BUCKET, 50,
            ModItems.WATER_CONTAINER, 65
            // Add more liquid items here
            // ModItems.COOLANT_CELL, 200,
            // ModItems.SUPER_COOLANT, 5000
    );

    private static final Map<Item, LiquidRefillRule> LIQUID_RULES = Map.of(
            Items.WATER_BUCKET, LiquidRefillRule.MISSING_AT_LEAST_VALUE,
            ModItems.WATER_CONTAINER, LiquidRefillRule.MISSING_AT_LEAST_VALUE
            // ModItems.COOLANT_CELL, LiquidRefillRule.MISSING_AT_LEAST_VALUE,
            // ModItems.SUPER_COOLANT, LiquidRefillRule.ONLY_WHEN_EMPTY
    );






    public RefineryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REFINERY_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RefineryBlockEntity.this.progress;
                    case 1 -> RefineryBlockEntity.this.maxProgress;
                    case 2 -> RefineryBlockEntity.this.liquidRemaining;
                    case 3 -> RefineryBlockEntity.this.liquidMax;
                    case 4 -> (int)((energyRemaining / (float)energyMax) * 50);
                    case 5 -> 50;

                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> RefineryBlockEntity.this.progress = value;
                    case 1 -> RefineryBlockEntity.this.maxProgress = value;
                    case 2 -> RefineryBlockEntity.this.liquidRemaining = value;
                    case 3 -> RefineryBlockEntity.this.liquidMax = value;
                    case 4 -> RefineryBlockEntity.this.energyRemaining = value;
                    case 5 -> RefineryBlockEntity.this.energyMax = value;
                }
            }

            @Override
            public int size() {
                return 6;
            }
        };

    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }



    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("refinery.progress", progress);
        nbt.putInt("refinery.max_progress", maxProgress);
        nbt.putInt("liquidRemaining", liquidRemaining);
        nbt.putInt("liquidMax", liquidMax);
        nbt.putInt("energyRemaining", energyRemaining);
        nbt.putInt("energyMax", energyMax);

    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("refinery.progress");
        maxProgress = nbt.getInt("refinery.max_progress");
        liquidRemaining = nbt.getInt("liquidRemaining");
        liquidMax = nbt.getInt("liquidMax");
        energyRemaining = nbt.getInt("energyRemaining");
        energyMax = nbt.getInt("energyMax");


    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) return;


        tryRefillLiquid();
        tryRefillEnergy();


        if(hasRecipe() && canInsertIntoOutputSlot()) {


            increaseCraftingProgress();
            world.setBlockState(pos, state.with(RefineryBlock.LIT, true));
            markDirty(world,pos,state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }

        } else {
            world.setBlockState(pos, state.with(RefineryBlock.LIT, false));
            resetProgress();
        }

    }
    private void tryRefillLiquid() {
        ItemStack stack = this.getStack(FLUID_ITEM_SLOT);
        if (stack.isEmpty()) return;

        Item item = stack.getItem();

        // Check if this item is a liquid source
        Integer value = LIQUID_VALUES.get(item);
        if (value == null) return;

        LiquidRefillRule rule = LIQUID_RULES.get(item);

        int missing = liquidMax - liquidRemaining;

        switch (rule) {
            case MISSING_AT_LEAST_VALUE -> {
                // Only refill if missing enough liquid
                if (missing >= value) {
                    liquidRemaining = Math.min(liquidRemaining + value, liquidMax);
                    stack.decrement(1);
                }
            }

            case ONLY_WHEN_EMPTY -> {
                // Only refill if tank is completely empty
                if (liquidRemaining == 0) {
                    liquidRemaining = Math.min(liquidRemaining + value, liquidMax);
                    stack.decrement(1);
                }
            }
        }
    }


    private void tryRefillEnergy() {
        ItemStack stack = this.getStack(ENERGY_ITEM_SLOT);
        if (stack.isEmpty()) return;

        Item item = stack.getItem();

        // Check if this item is an energy source
        Integer value = ENERGY_VALUES.get(item);
        if (value == null) return;

        EnergyRefillRule rule = ENERGY_RULES.get(item);

        int missing = energyMax - energyRemaining;

        switch (rule) {
            case MISSING_AT_LEAST_VALUE -> {
                // Only refill if missing enough energy
                if (missing >= value) {
                    energyRemaining = Math.min(energyRemaining + value, energyMax);
                    stack.decrement(1);
                }
            }

            case ONLY_WHEN_EMPTY -> {
                // Only refill if tank is completely empty
                if (energyRemaining == 0) {
                    energyRemaining = Math.min(energyRemaining + value, energyMax);
                    stack.decrement(1);
                }
            }
        }
    }









    private void resetProgress() {
        this.progress = 0;

    }

    private void craftItem() {
        Optional<RecipeEntry<RefineryRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack result = recipe.get().value().output().copy();
        result.setCount(recipe.get().value().count());

        // consume input
        this.removeStack(INPUT_SLOT, 1);

        // insert output
        ItemStack outputSlot = this.getStack(OUTPUT_SLOT);
        if (outputSlot.isEmpty()) {
            this.setStack(OUTPUT_SLOT, result);
        } else {
            outputSlot.increment(result.getCount());
        }

        // NEW: drain resources per craft
        liquidRemaining -= 1;   // or whatever amount you want
        energyRemaining -= 1;   // or whatever amount you want

        // clamp to zero
        if (liquidRemaining < 0) liquidRemaining = 0;
        if (energyRemaining < 0) energyRemaining = 0;
    }



    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean canInsertIntoOutputSlot() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
            this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<RefineryRecipe>> optional = getCurrentRecipe();
        if (optional.isEmpty()) return false;

        if (liquidRemaining <= 0 || energyRemaining <= 0) return false;

        RefineryRecipe recipe = optional.get().value();

        this.maxProgress = recipe.processingTime();

        ItemStack output = recipe.output().copy();
        output.setCount(recipe.count());

        return canInsertAmountIntoOutputSlot(output.getCount())
                && canInsertItemIntoOutputSlot(output);
    }





    private Optional<RecipeEntry<RefineryRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager().getFirstMatch(ModRecipes.REFINERY_TYPE, new RefineryRecipeInput(inventory.get(INPUT_SLOT)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();
        return maxCount >= currentCount + count;
    }



    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    /* Adding a Screen */

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.lksls.refinery");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new RefineryScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
    public boolean isFluidItem(ItemStack stack) {
        return LIQUID_VALUES.containsKey(stack.getItem());
    }

    public boolean isEnergyItem(ItemStack stack) {
        return ENERGY_VALUES.containsKey(stack.getItem());
    }

    public boolean isValidInput(ItemStack stack) {
        return !isFluidItem(stack) && !isEnergyItem(stack);
    }

}

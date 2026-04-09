package net.lksls.legendaryserver.block.entity;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.block.custom.ChemicalTntBlock;
import net.lksls.legendaryserver.block.entity.custom.ChemicalTntEntity;
import net.lksls.legendaryserver.block.entity.custom.CoreFusionerBlockEntity;
import net.lksls.legendaryserver.block.entity.custom.CustomSpawnerBlockEntity;
import net.lksls.legendaryserver.block.entity.custom.RefineryBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CoreFusionerBlockEntity> CORE_FUSIONER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendaryServerMod.MOD_ID, "core_fusioner_be"),
                    BlockEntityType.Builder.create(CoreFusionerBlockEntity::new, ModBlocks.CORE_FUSIONER_BLOCK).build(null));
    public static final BlockEntityType<RefineryBlockEntity>REFINERY_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendaryServerMod.MOD_ID, "refinery_be"),
                    BlockEntityType.Builder.create(RefineryBlockEntity::new, ModBlocks.REFINERY).build(null));
//    public static final BlockEntityType<RefineryBlockEntity>CHEMICAL_TNT_BE =
//            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendaryServerMod.MOD_ID, "chemical_tnt_be"),
//                    BlockEntityType.Builder.create(RefineryBlockEntity::new, ModBlocks.CHEMICAL_TNT).build(null));
 //         Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(LegendaryServerMod.MOD_ID, "chemical_tnt_be"),
   //                 BlockEntityType.Builder.create(ChemicalTntEntity::new, ModBlocks.CHEMICAL_TNT).build(null));

    public static final BlockEntityType<CustomSpawnerBlockEntity> CUSTOM_SPAWNER_BE =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(LegendaryServerMod.MOD_ID, "custom_spawner_be"),
                    BlockEntityType.Builder.create(
                            CustomSpawnerBlockEntity::new,
                            ModBlocks.CUSTOM_SPAWNER_BLOCK
                    ).build(null)
            );


    public static void registerBlockEntities() {
        LegendaryServerMod.LOGGER.info("Registering Block Entities for " + LegendaryServerMod.MOD_ID);
    }
}

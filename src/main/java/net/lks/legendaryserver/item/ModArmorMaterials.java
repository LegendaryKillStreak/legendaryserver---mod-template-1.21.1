package net.lks.legendaryserver.item;

import com.google.common.base.Suppliers;
import net.lks.legendaryserver.LegendaryServerMod;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> LIGHT_CORE_ARMOR_MATERIAL = registerArmorMaterial("light_core",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.BODY, 15);
            }), 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItems.LIGHT_CORE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(LegendaryServerMod.MOD_ID, "light_core"))),4.0F, 0.1F));


    public static final RegistryEntry<ArmorMaterial> DARK_CORE_ARMOR_MATERIAL = registerArmorMaterial("dark_core",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 11);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.BODY, 16);
            }), 27, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(ModItems.DARK_CORE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(LegendaryServerMod.MOD_ID, "dark_core"))),5.0F, 0.2F));
    private int baseMaxDamage;


    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(LegendaryServerMod.MOD_ID, name), material.get());
    }
    
    public int getMaxDamage(int multiplier) {
        return this.baseMaxDamage * multiplier;
    }






}

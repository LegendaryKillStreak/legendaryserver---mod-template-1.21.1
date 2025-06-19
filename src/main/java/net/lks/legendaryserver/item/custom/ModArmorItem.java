package net.lks.legendaryserver.item.custom;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import jdk.jshell.Snippet;
import net.lks.legendaryserver.item.ModArmorMaterials;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureData;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.DARK_CORE_ARMOR_MATERIAL,
                            List.of(new StatusEffectInstance(StatusEffects.RESISTANCE,30,1,false,false),
                                    new StatusEffectInstance(StatusEffects.STRENGTH,30,0,false,false),
                                    new StatusEffectInstance(StatusEffects.DARKNESS,30,0,false,false)))

                    .put(ModArmorMaterials.LIGHT_CORE_ARMOR_MATERIAL,
                            List.of(new StatusEffectInstance(StatusEffects.DARKNESS, 0, 1, false, false),
                                    new StatusEffectInstance(StatusEffects.NIGHT_VISION,400,1,false,false),
                                    new StatusEffectInstance(StatusEffects.HASTE,20,0,false,false),
                                    new StatusEffectInstance(StatusEffects.GLOWING,20,0,false,false),
                                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST,200,1,false,false))).build();






    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player) {
                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);


    }
    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }

        }
    }


    private void addStatusEffectForMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance-> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            for(StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
     }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

     private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        for(ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
         ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
         ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
         ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
         ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

         return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                 leggings.getMaterial() == material && boots.getMaterial() == material;

    }
    @Override
    public int getEnchantability() {
        return this.material.value().enchantability();
    }

    public RegistryEntry<ArmorMaterial> getMaterial() {
        return this.material;
    }


    }




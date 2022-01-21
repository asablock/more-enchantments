package com.github.asablock.enchantments;

import com.github.asablock.UsefulEnchantmentsMod;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.enchantment.FlameEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FreezingEnchantment extends DEnchantment {
    public FreezingEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof FireAspectEnchantment || other instanceof FlameEnchantment);
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return isDisabled() ? 0f : 2.5f;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (isDisabled()) return;
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
                    100 * level, level));
            if (UsefulEnchantmentsMod.CONFIG.getOrDefault("doIcePlacingOnFreezing", true))
                target.world.setBlockState(target.getBlockPos(), Blocks.ICE.getDefaultState());
        }
    }
}

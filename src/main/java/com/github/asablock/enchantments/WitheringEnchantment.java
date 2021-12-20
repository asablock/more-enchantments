package com.github.asablock.enchantments;

import com.github.asablock.UsefulEnchantmentsMod;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;

public class WitheringEnchantment extends DEnchantment {
    public WitheringEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof ToxicEnchantment);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity entityTarget, int level) {
        if (isDisabled()) return;
        if (entityTarget instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) entityTarget;
            if ((target instanceof WitherEntity || target instanceof WitherSkeletonEntity) &&
                    UsefulEnchantmentsMod.CONFIG.getOrDefault("doWitherCreatureHealingOnWithering", true)
            ) {
                target.heal(level * 5);
            } else {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,
                        level * 100, level));
                if (UsefulEnchantmentsMod.CONFIG.getOrDefault("doWitherRosePlacingOnWithering", true))
                    target.world.setBlockState(target.getBlockPos(),
                            Blocks.WITHER_ROSE.getDefaultState());
            }
        }
    }
}

package com.github.asablock.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.explosion.Explosion;

public class ExplodingEnchantment extends DEnchantment {
    public ExplodingEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof FireAspectEnchantment || other instanceof FreezingEnchantment);
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return isDisabled() ? 0f : -3f + level;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (isDisabled()) return;
        if (target instanceof LivingEntity) {
            target.world.createExplosion(target, target.getX(), target.getY(), target.getZ(),
                    level * 1.5f, true, Explosion.DestructionType.BREAK);
        }
    }
}

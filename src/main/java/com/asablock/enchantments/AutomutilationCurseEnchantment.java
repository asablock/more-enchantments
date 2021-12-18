package com.asablock.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class AutomutilationCurseEnchantment extends DEnchantment {
    public AutomutilationCurseEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof WitheringEnchantment ||
                other instanceof ToxicEnchantment || other instanceof FreezingEnchantment);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (isDisabled()) return;
        if (target instanceof LivingEntity) {
            float damage = user.getMainHandStack().getDamage();
            user.damage(DamageSource.mob(user), damage);
            ((LivingEntity) target).heal(damage);
        } else {
            user.damage(DamageSource.mob(user), 1);
        }
    }
}

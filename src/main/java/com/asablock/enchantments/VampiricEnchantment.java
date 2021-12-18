package com.asablock.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class VampiricEnchantment extends DEnchantment {
    public VampiricEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
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
    public boolean canAccept(Enchantment other) {
        return true;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity entity, int level) {
        if (isDisabled()) return;
        if (entity instanceof LivingEntity) {
            user.heal((float) (level * .75 - user.getRandom().nextInt(5) * .1));
        }
    }
}

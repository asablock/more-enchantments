package com.asablock.enchantments;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;

public class SpikesEnchantment extends DEnchantment {
    public SpikesEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof ImpalingEnchantment || other instanceof DamageEnchantment);
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        if (isDisabled()) return 0;
        return (float) (2 + (level - 1) * 1.5);
    }
}

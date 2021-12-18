package com.asablock.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;

public class BluntEnchantment extends DEnchantment {
    public BluntEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return isDisabled() ? 0f : -1f * level;
    }
}

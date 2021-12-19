package com.github.asablock.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class ArrowRecyclingEnchantment extends DEnchantment {
    public ArrowRecyclingEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.BOW, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof InfinityEnchantment);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}

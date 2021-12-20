package com.github.asablock.enchantments;

import com.github.asablock.UsefulEnchantmentsMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

import java.util.Arrays;

public abstract class DEnchantment extends Enchantment {
    protected DEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    public final boolean isDisabled() {
        return Arrays.binarySearch(
                UsefulEnchantmentsMod.DISABLED_ENCHANTMENTS,
                getClass().getSimpleName()) > 0;
    }
}

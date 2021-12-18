package com.asablock.enchantments;

import com.asablock.MoreEnchantmentsMod;
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
                MoreEnchantmentsMod.DISABLED_ENCHANTMENTS,
                getClass().getSimpleName()) > 0;
    }
}

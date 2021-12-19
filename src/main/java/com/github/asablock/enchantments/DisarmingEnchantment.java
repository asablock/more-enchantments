package com.github.asablock.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class DisarmingEnchantment extends DEnchantment {
    public DisarmingEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (isDisabled()) return;
        boolean isPlayer = user instanceof PlayerEntity;
        PlayerEntity player = isPlayer ? (PlayerEntity) user : null;
        if (target instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) target;
            if (!e.getMainHandStack().isEmpty()) {
                if (isPlayer) player.giveItemStack(e.getMainHandStack());
                e.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
            } else if (!e.getOffHandStack().isEmpty()) {
                if (isPlayer) player.giveItemStack(e.getOffHandStack());
                e.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
                if (isPlayer) player.giveItemStack(e.getEquippedStack(EquipmentSlot.HEAD));
                e.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
                if (isPlayer) player.giveItemStack(e.getEquippedStack(EquipmentSlot.CHEST));
                e.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.LEGS).isEmpty()) {
                if (isPlayer) player.giveItemStack(e.getEquippedStack(EquipmentSlot.LEGS));
                e.equipStack(EquipmentSlot.LEGS, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.FEET).isEmpty()) {
                if (isPlayer) player.giveItemStack(e.getEquippedStack(EquipmentSlot.FEET));
                e.equipStack(EquipmentSlot.FEET, ItemStack.EMPTY);
            }
            // Is it too redundancy?
        }
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}

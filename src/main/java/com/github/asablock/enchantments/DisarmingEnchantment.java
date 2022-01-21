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
        if (target instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) target;
            if (!e.getMainHandStack().isEmpty()) {
                ifPlayerGiveStack(user, e.getMainHandStack());
                e.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
            } else if (!e.getOffHandStack().isEmpty()) {
                ifPlayerGiveStack(user, e.getOffHandStack());
                e.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
                ifPlayerGiveStack(user, e.getEquippedStack(EquipmentSlot.HEAD));
                e.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
                ifPlayerGiveStack(user, e.getEquippedStack(EquipmentSlot.CHEST));
                e.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.LEGS).isEmpty()) {
                ifPlayerGiveStack(user, e.getEquippedStack(EquipmentSlot.LEGS));
                e.equipStack(EquipmentSlot.LEGS, ItemStack.EMPTY);
            } else if (!e.getEquippedStack(EquipmentSlot.FEET).isEmpty()) {
                ifPlayerGiveStack(user, e.getEquippedStack(EquipmentSlot.FEET));
                e.equipStack(EquipmentSlot.FEET, ItemStack.EMPTY);
            }
            // Is it too redundancy?
        }
    }

    private void ifPlayerGiveStack(Entity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity) entity).giveItemStack(stack);
        }
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}

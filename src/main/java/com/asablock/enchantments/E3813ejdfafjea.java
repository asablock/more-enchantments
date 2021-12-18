package com.asablock.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class E3813ejdfafjea extends DEnchantment {
    public E3813ejdfafjea(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (isDisabled()) System.out.println("I am E3813ejdfafjea! You cannot disable me!!!");
        if (user instanceof PlayerEntity && target instanceof LivingEntity) {
            ((PlayerEntity) user).sendMessage(Text.of("I attacked a creature!"), false);
        }
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (isDisabled()) System.out.println("I am E3813ejdfafjea! You cannot disable me!!!");
        if (user instanceof PlayerEntity && attacker instanceof LivingEntity) {
            ((PlayerEntity) user).sendMessage(Text.of("A creature attacked me!"), false);
        }
    }

    @Override
    public int getMinPower(int level) {
        return 0;
    }

    @Override
    public int getMaxPower(int level) {
        return 1;
    }
}

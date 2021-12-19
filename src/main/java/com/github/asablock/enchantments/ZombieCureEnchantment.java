package com.github.asablock.enchantments;

import com.github.asablock.mixin.ZombeVillagerEntityInvoker;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.server.world.ServerWorld;

public class ZombieCureEnchantment extends DEnchantment {
    public ZombieCureEnchantment(Rarity rarity, EquipmentSlot[] slotTypes) {
        super(rarity, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        // Conflict with anything
        return false;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity entity, int level) {
        if (isDisabled()) return;
        if (entity instanceof ZombieVillagerEntity) {
            ((ZombeVillagerEntityInvoker) (ZombieVillagerEntity) entity).invokeFinishConversion((ServerWorld) entity.world);
        }
    }
}

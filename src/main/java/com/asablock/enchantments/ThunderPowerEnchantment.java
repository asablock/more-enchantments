package com.asablock.enchantments;

import net.minecraft.enchantment.ChannelingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;

public class ThunderPowerEnchantment extends DEnchantment {
    public ThunderPowerEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof ChannelingEnchantment || other instanceof FireAspectEnchantment);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (isDisabled()) return;
        if (target instanceof LivingEntity) {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(target.world);
            lightningEntity.refreshPositionAfterTeleport(target.getPos());
            lightningEntity.setChanneler(user instanceof ServerPlayerEntity ? (ServerPlayerEntity) user : null);
            target.world.spawnEntity(lightningEntity);
            user.playSound(SoundEvents.ITEM_TRIDENT_THUNDER, 5f, 1f);
        }
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}

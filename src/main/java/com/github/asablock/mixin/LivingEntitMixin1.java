package com.github.asablock.mixin;

import com.github.asablock.UsefulEnchantmentsMod;
import com.github.asablock.enchantments.DEnchantment;
import com.github.asablock.enchantments.ObsidianWalkerEnchantment;
import com.github.asablock.mixinsupports.SoulSpeedSupporter;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntitMixin1 {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyMovementEffects(Lnet/minecraft/util/math/BlockPos;)V"), method = "baseTick")
    private void applyMovementEffects(LivingEntity instance, BlockPos pos) {
        int i = EnchantmentHelper.getEquipmentLevel(UsefulEnchantmentsMod.OBSIDIAN_WALKER_ENCHANTMENT, instance);
        if (i > 0 && !((DEnchantment) UsefulEnchantmentsMod.OBSIDIAN_WALKER_ENCHANTMENT).isDisabled()) {
            ObsidianWalkerEnchantment.coolingLava(instance.world, pos, i, instance.isOnGround(),
                    instance.getPos(), instance.getRandom());
        }
        int i2 = EnchantmentHelper.getEquipmentLevel(Enchantments.FROST_WALKER, instance);
        if (i > 0) {
            FrostWalkerEnchantment.freezeWater(instance, instance.world, pos, i);
        }
        SoulSpeedSupporter.soulSpeedDetector(instance);
    }
}

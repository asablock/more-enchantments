package com.github.asablock.mixin;

import com.github.asablock.UsefulEnchantmentsMod;
import com.github.asablock.enchantments.DEnchantment;
import com.github.asablock.enchantments.ObsidianWalkerEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntitMixin1 {
    @Shadow public abstract Random getRandom();

    @Inject(method = "applyMovementEffects", at = @At("HEAD"))
    private void applyMovementEffects(BlockPos pos, CallbackInfo info) {
        LivingEntity inst = (LivingEntity) (Object) this;
        int i = EnchantmentHelper.getEquipmentLevel(UsefulEnchantmentsMod.OBSIDIAN_WALKER_ENCHANTMENT, inst);
        if (i > 0 && !((DEnchantment) UsefulEnchantmentsMod.OBSIDIAN_WALKER_ENCHANTMENT).isDisabled()) {
            ObsidianWalkerEnchantment.coolingLava(inst.world, pos, i, inst.isOnGround(), inst.getPos(),
                    getRandom());
        }
    }
}

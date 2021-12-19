package com.github.asablock.mixin;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    /*@Redirect(method = "applyMovementEffects(Lnet/minecraft/util/math/BlockPos;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyMovementEffects(Lnet/minecraft/util/math/BlockPos;)V"))
    private void applyMovementEffects(LivingEntity entity, BlockPos pos) {
        LivinEntityAccessor ent = (LivinEntityAccessor) entity;
        int i = EnchantmentHelper.getEquipmentLevel(Mod.OBSIDIAN_WALKER_ENCHANTMENT, entity);
        if (i > 0 && !((DEnchantment) Mod.OBSIDIAN_WALKER_ENCHANTMENT).isDisabled()) {
            ObsidianWalkerEnchantment.coolingLava(entity, entity.world, pos, i);
        }
        int i2 = EnchantmentHelper.getEquipmentLevel(Enchantments.FROST_WALKER, entity);
        if (i2 > 0) {
            FrostWalkerEnchantment.freezeWater(entity, entity.world, pos, i2);
        }

        SoulSpeedSupporter.soulSpeedDetector(entity);
    }*/
    @Inject(method = "openEditSignScreen(Lnet/minecraft/block/entity/SignBlockEntity;)V", at = @At("HEAD"))
    private void openEditSignScreen(SignBlockEntity entity, CallbackInfo info) {

    }
}

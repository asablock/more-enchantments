package com.asablock.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivinEntityAccessor {
    @Invoker("method_29500")
    public boolean invokeMethod_29500(BlockState state);

    @Invoker("removeSoulSpeedBoost")
    public void invokeRemoveSoulSpeedBoost();

    @Invoker("addSoulSpeedBoostIfNeeded")
    public void invokeAddSoulSpeedBoostIfNeeded();
}

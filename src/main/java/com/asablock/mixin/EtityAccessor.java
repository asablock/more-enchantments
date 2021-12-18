package com.asablock.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EtityAccessor {
    @Invoker("getLandingBlockState")
    public BlockState invokeGetLandingBlockState();
}

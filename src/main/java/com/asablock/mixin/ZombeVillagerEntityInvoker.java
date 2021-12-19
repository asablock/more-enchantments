package com.asablock.mixin;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ZombieVillagerEntity.class)
public interface ZombeVillagerEntityInvoker {
    @Invoker("finishConversion")
    public void invokeFinishConversion(ServerWorld world);
}

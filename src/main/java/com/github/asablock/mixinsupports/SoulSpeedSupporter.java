package com.github.asablock.mixinsupports;

import com.github.asablock.mixin.EtityAccessor;
import com.github.asablock.mixin.LivinEntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;

public final class SoulSpeedSupporter {
    private SoulSpeedSupporter() {
    }

    public static void soulSpeedDetector(LivingEntity entity) {
        LivinEntityAccessor accessor = (LivinEntityAccessor) entity;

        BlockState landingBlockState = ((EtityAccessor) entity).invokeGetLandingBlockState();
        if (accessor.invokeMethod_29500(landingBlockState)) {
            accessor.invokeRemoveSoulSpeedBoost();
        }

        accessor.invokeAddSoulSpeedBoostIfNeeded();
    }
}

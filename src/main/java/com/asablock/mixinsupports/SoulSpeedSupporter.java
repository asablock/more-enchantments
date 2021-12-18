package com.asablock.mixinsupports;

import com.asablock.mixin.EtityAccessor;
import com.asablock.mixin.LivinEntityAccessor;
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

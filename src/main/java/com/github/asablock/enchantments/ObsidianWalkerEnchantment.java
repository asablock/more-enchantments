package com.github.asablock.enchantments;

import com.github.asablock.UsefulEnchantmentsMod;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ObsidianWalkerEnchantment extends DEnchantment {
    public ObsidianWalkerEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof FrostWalkerEnchantment);
    }

    public static void coolingLava(LivingEntity entity, World world, BlockPos blockPos, int level) {
        if (entity.isOnGround()) {
            Block block = UsefulEnchantmentsMod.CONFIG.getOrDefault("doObsidianWalkerPlacingUnstableObsidian", true)
                    ? UsefulEnchantmentsMod.UNSTABLE_OBSIDIAN_BLOCK : Blocks.OBSIDIAN;
            BlockState blockState = block.getDefaultState();
            float f = (float) Math.min(16, 2 + level);
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-f, -1d, -f), blockPos.add(f,
                    -1d, f))) {
                if (blockPos2.isWithinDistance(entity.getPos(), f)) {
                    mutable.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                    BlockState blockState2 = world.getBlockState(mutable);
                    if (blockState2.isAir()) {
                        BlockState blockState3 = world.getBlockState(blockPos2);
                        if (blockState3.getMaterial() == Material.LAVA && blockState3.get(FluidBlock.LEVEL)
                                == 0 && blockState.canPlaceAt(world, blockPos2) && world
                                .canPlace(blockState, blockPos2, ShapeContext.absent())) {
                            world.setBlockState(blockPos2, blockState);
                            world.getBlockTickScheduler().schedule(blockPos2,
                                    block, MathHelper.nextInt(entity.getRandom(),
                                            60, 120));
                        }
                    }
                }
            }
        }
    }
}

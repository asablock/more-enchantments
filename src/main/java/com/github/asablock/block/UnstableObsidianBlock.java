package com.github.asablock.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings("deprecation")
public class UnstableObsidianBlock extends Block {
    public static final IntProperty AGE;

    public UnstableObsidianBlock(Settings settings) {
        super(settings);
        //模组首发于 mcmod.cn
        setDefaultState(getDefaultState().with(AGE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        scheduledTick(state, world, pos, random);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(3) == 0 || this.canMelt(world, pos) &&
                world.getLightLevel(pos) > 11 - state.get(AGE) && increaseAge(state, world, pos)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            Direction[] var6 = Direction.values();
            int var7 = var6.length;

            for (Direction direction : var6) {
                mutable.set(pos, direction);
                BlockState blockState = world.getBlockState(mutable);
                if (blockState.isOf(this) && !this.increaseAge(blockState, world, mutable)) {
                    world.getBlockTickScheduler().schedule(mutable, this, MathHelper.nextInt(random, 20, 40));
                }
            }
        } else {
            world.getBlockTickScheduler().schedule(pos, this, MathHelper.nextInt(random, 20, 40));
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block,
                               BlockPos fromPos, boolean notify) {
        if (block == this && canMelt(world, pos)) {
            melt(state, world, pos);
        }

        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }

    private boolean increaseAge(BlockState state, World world, BlockPos pos) {
        int i = state.get(AGE);
        if (i < 3) {
            world.setBlockState(pos, state.with(AGE, i + 1), 2);
            return false;
        } else {
            melt(state, world, pos);
            return true;
        }
    }

    private boolean canMelt(BlockView world, BlockPos pos) {
        int i = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction[] directions = Direction.values();
        for (Direction direction : directions) {
            mutable.set(pos, direction);
            if (world.getBlockState(mutable).isOf(this)) {
                ++i;
                if (i >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public void melt(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        world.updateNeighbor(pos, Blocks.LAVA, pos);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public void afterBreak(World world, PlayerEntity entity, BlockPos pos,
                           BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, entity, pos, state, blockEntity, stack);
        Material material = world.getBlockState(pos.down()).getMaterial();
        if (material.blocksMovement() || material.isLiquid()) {
            world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.NORMAL;
    }

    static {
        AGE = Properties.AGE_3;
    }
}

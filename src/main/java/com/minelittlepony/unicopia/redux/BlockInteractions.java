package com.minelittlepony.unicopia.redux;

import java.util.List;

import com.minelittlepony.unicopia.core.magic.spell.SpellRegistry;
import com.minelittlepony.unicopia.redux.block.ITillable;
import com.minelittlepony.unicopia.redux.block.UBlocks;
import com.minelittlepony.unicopia.redux.item.UItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInteractions {
    public boolean onBlockTilled(World world, BlockPos pos, PlayerEntity player, ItemStack hoe) {
        BlockState state = world.getBlockState(pos);

        if (!(state.getBlock() instanceof ITillable)) {
            return false;
        }

        ITillable farm = ((ITillable)state.getBlock());

        if (!farm.canBeTilled(hoe, player, world, state, pos)) {
            return false;
        }

        world.setBlockState(pos, farm.getFarmlandState(hoe, player, world, state, pos));

        return true;
    }

    public void addAuxiliaryDrops(World world, BlockState state, BlockPos pos, List<ItemStack> drops, int fortune) {
        Block block = state.getBlock();

        int fortuneFactor = 1 + fortune * 15;

        if (block == Blocks.STONE) {
            if (world.random.nextInt(500 / fortuneFactor) == 0) {
                for (int i = 0; i < 1 + fortune; i++) {
                    if (world.random.nextInt(10) > 3) {
                        drops.add(new ItemStack(UItems.curse));
                    } else {
                        drops.add(new ItemStack(UItems.spell));
                    }
                }
            }

            if (world.random.nextInt(5000) == 0) {
                drops.add(SpellRegistry.instance().enchantStack(new ItemStack(UItems.spell), "awkward"));
            }
        } else if (block == Blocks.DIRT || block == Blocks.CLAY || block == Blocks.GRASS_PATH || block == Blocks.GRASS || block == UBlocks.hive) {
            if (world.random.nextInt(25 / fortuneFactor) == 0) {
                drops.add(new ItemStack(UItems.wheat_worms, 1 + fortune));
            }
        } else if (block instanceof GrassBlock) {
            if (world.random.nextInt(25 / fortuneFactor) == 0) {
                for (int i = 0; i < 1 + fortune; i++) {
                    int chance = world.random.nextInt(3);

                    if (chance == 0) {
                        drops.add(new ItemStack(UItems.alfalfa_seeds));
                    } else if (chance == 1) {
                        drops.add(new ItemStack(UItems.apple_seeds));
                    } else {
                        drops.add(new ItemStack(UItems.tomato_seeds));
                    }
                }
            }
        }
    }
}
package com.minelittlepony.unicopia.redux.magic.spells;

import javax.annotation.Nullable;

import com.minelittlepony.unicopia.core.magic.AbstractSpell;
import com.minelittlepony.unicopia.core.magic.Affinity;
import com.minelittlepony.unicopia.core.magic.CastResult;
import com.minelittlepony.unicopia.core.magic.ICaster;
import com.minelittlepony.unicopia.core.magic.IDispenceable;
import com.minelittlepony.unicopia.core.magic.IUseable;
import com.minelittlepony.unicopia.core.util.MagicalDamageSource;
import com.minelittlepony.unicopia.core.util.PosHelper;
import com.minelittlepony.unicopia.core.util.VecHelper;
import com.minelittlepony.unicopia.core.util.collection.IStateMapping;
import com.minelittlepony.unicopia.core.util.collection.StateMapList;
import com.minelittlepony.unicopia.core.util.shape.IShape;
import com.minelittlepony.unicopia.core.util.shape.Sphere;
import com.minelittlepony.unicopia.redux.UMaterials;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class IceSpell extends AbstractSpell.RangedAreaSpell implements IUseable, IDispenceable {

    public final StateMapList affected = new StateMapList();

    public IceSpell() {
        affected.add(IStateMapping.build(
                s -> s.getMaterial() == Material.WATER,
                s -> Blocks.ICE.getDefaultState()));
        affected.add(IStateMapping.build(
                s -> s.getMaterial() == Material.LAVA,
                s -> Blocks.OBSIDIAN.getDefaultState()));
        affected.add(IStateMapping.build(
                s -> s.getBlock() == Blocks.SNOW,
                s -> {
                    s = s.cycle(SnowBlock.LAYERS);
                    if (s.get(SnowBlock.LAYERS) >= 7) {
                        return Blocks.SNOW.getDefaultState();
                    }

                    return s;
                }));
        affected.replaceBlock(Blocks.FIRE, Blocks.AIR);
        affected.setProperty(Blocks.REDSTONE_WIRE, RedstoneWireBlock.POWER, 0);
    }

    private final int rad = 3;
    private final IShape effect_range = new Sphere(false, rad);

    @Override
    public String getName() {
        return "ice";
    }

    @Override
    public Affinity getAffinity() {
        return Affinity.GOOD;
    }

    @Override
    public int getTint() {
        return 0xBDBDF9;
    }

    @Override
    public boolean update(ICaster<?> source) {
        return false;
    }

    @Override
    public void render(ICaster<?> source) {
    }

    @Override
    public CastResult onDispenced(BlockPos pos, Direction facing, BlockPointer source, Affinity affinity) {
        return applyBlocks(null, source.getWorld(), pos.offset(facing, rad)) ? CastResult.NONE : CastResult.DEFAULT;
    }

    @Override
    public CastResult onUse(ItemUsageContext context, Affinity affinity) {
        if (context.getPlayer() != null && context.getPlayer().isSneaking()) {
            applyBlockSingle(context.getPlayer(), context.getWorld(), context.getBlockPos());
        } else {
            applyBlocks(context.getPlayer(), context.getWorld(), context.getBlockPos());
        }

        return CastResult.DEFAULT;
    }

    @Override
    public CastResult onUse(ItemStack stack, Affinity affinity, PlayerEntity player, World world, @Nullable Entity hitEntity) {
        if (hitEntity != null && applyEntitySingle(player, hitEntity)) {
            return CastResult.DEFAULT;
        }

        return CastResult.NONE;
    }

    private boolean applyBlocks(PlayerEntity owner, World world, BlockPos pos) {

        for (BlockPos i : PosHelper.getAllInRegionMutable(pos, effect_range)) {
            applyBlockSingle(owner, world, i);
        }

        return applyEntities(owner, world, pos);
    }

    protected boolean applyEntities(PlayerEntity owner, World world, BlockPos pos) {
        return VecHelper.findAllEntitiesInRange(owner, world, pos, 3).filter(i ->
            applyEntitySingle(owner, i)
        ).count() > 0;
    }

    protected boolean applyEntitySingle(PlayerEntity owner, Entity e) {
        if (e instanceof TntEntity) {
            e.remove();
            e.getEntityWorld().setBlockState(e.getBlockPos(), Blocks.TNT.getDefaultState());
        } else if (e.isOnFire()) {
            e.extinguish();
        } else {
            e.damage(MagicalDamageSource.causePlayerDamage("cold", owner), 2);
        }

        return true;
    }

    private void applyBlockSingle(PlayerEntity owner, World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Block id = state.getBlock();

        BlockState converted = affected.getConverted(state);

        if (!state.equals(converted)) {
            world.setBlockState(pos, converted, 3);
        } else if (state.getMaterial() != UMaterials.cloud && world.doesBlockHaveSolidTopSurface(pos, owner)
                || (id == Blocks.SNOW)
                || state.matches(BlockTags.LEAVES)) {
            incrementIce(world, pos.up());
        } else if (state.getMaterial() == Material.ICE && world.random.nextInt(10) == 0) {
            if (isSurroundedByIce(world, pos)) {
                world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
            }
        }

        world.addParticle(ParticleTypes.SPLASH, pos.getX() + world.random.nextFloat(), pos.getY() + 1, pos.getZ() + world.random.nextFloat(), 0, 0, 0);
    }

    public static boolean isSurroundedByIce(World w, BlockPos pos) {
        return !PosHelper.adjacentNeighbours(pos).stream().anyMatch(i ->
            w.getBlockState(i).getMaterial() == Material.ICE
        );
    }

    private void incrementIce(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Block id = state.getBlock();

        if (id == Blocks.AIR || (id instanceof PlantBlock)) {
            world.setBlockState(pos, Blocks.SNOW.getDefaultState(), 3);
        }
    }
}
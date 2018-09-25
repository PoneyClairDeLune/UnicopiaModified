package com.minelittlepony.unicopia.item;

import com.minelittlepony.unicopia.entity.EntitySpell;
import com.minelittlepony.unicopia.spell.IDispenceable;
import com.minelittlepony.unicopia.spell.IMagicEffect;
import com.minelittlepony.unicopia.spell.SpellCastResult;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICastable extends IMagicalItem {

    SpellCastResult onDispenseSpell(IBlockSource source, ItemStack stack, IDispenceable effect);

    SpellCastResult onCastSpell(EntityPlayer player, World world, BlockPos pos, ItemStack stack, IMagicEffect effect, EnumFacing side, float hitX, float hitY, float hitZ);

    boolean canFeed(EntitySpell spell, ItemStack stack);

}
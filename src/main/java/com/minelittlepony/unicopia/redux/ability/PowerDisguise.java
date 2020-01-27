package com.minelittlepony.unicopia.redux.ability;


import javax.annotation.Nullable;

import org.lwjgl.glfw.GLFW;

import com.minelittlepony.unicopia.core.Race;
import com.minelittlepony.unicopia.core.SpeciesList;
import com.minelittlepony.unicopia.core.UParticles;
import com.minelittlepony.unicopia.core.ability.Hit;
import com.minelittlepony.unicopia.core.ability.PowerFeed;
import com.minelittlepony.unicopia.core.entity.InAnimate;
import com.minelittlepony.unicopia.core.entity.player.IPlayer;
import com.minelittlepony.unicopia.core.util.VecHelper;
import com.minelittlepony.unicopia.redux.magic.spells.DisguiseSpell;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

/**
 * Changeling ability to disguise themselves as other players.
 */
public class PowerDisguise extends PowerFeed {

    @Override
    public String getKeyName() {
        return "unicopia.power.disguise";
    }

    @Override
    public int getKeyCode() {
        return GLFW.GLFW_KEY_P;
    }

    @Nullable
    @Override
    public Hit tryActivate(IPlayer player) {
        return new Hit();
    }

    @Override
    public void apply(IPlayer iplayer, Hit data) {
        PlayerEntity player = iplayer.getOwner();
        HitResult trace = VecHelper.getObjectMouseOver(player, 10, 1);

        Entity looked = null;

        if (trace.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult)trace).getBlockPos();

            if (!iplayer.getWorld().isAir(pos)) {
                BlockState state = iplayer.getWorld().getBlockState(pos);

                looked = new FallingBlockEntity(player.getEntityWorld(), 0, 0, 0, state);
            }
        } else if (trace.getType() == HitResult.Type.ENTITY) {
            looked = ((EntityHitResult)trace).getEntity();

            if (looked instanceof PlayerEntity) {
                looked = SpeciesList.instance().getPlayer((PlayerEntity)looked)
                        .getEffect(DisguiseSpell.class)
                        .map(DisguiseSpell::getDisguise)
                        .orElse(looked);
            }

            if (looked instanceof LightningEntity
            || (looked instanceof InAnimate && !((InAnimate)looked).canInteract(Race.CHANGELING))) {
                looked = null;
            }
        }

        player.getEntityWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PARROT_IMITATE_POLAR_BEAR, SoundCategory.PLAYERS, 1.4F, 0.4F);

        iplayer.getEffect(DisguiseSpell.class).orElseGet(() -> {
            DisguiseSpell disc = new DisguiseSpell();

            iplayer.setEffect(disc);
            return disc;
        }).setDisguise(looked);

        iplayer.sendCapabilities(true);
    }

    @Override
    public void preApply(IPlayer player) {
        player.addEnergy(2);
        player.spawnParticles(UParticles.CHANGELING_MAGIC, 5);
    }

    @Override
    public void postApply(IPlayer player) {
        player.setEnergy(0);
        player.spawnParticles(UParticles.CHANGELING_MAGIC, 5);
    }
}
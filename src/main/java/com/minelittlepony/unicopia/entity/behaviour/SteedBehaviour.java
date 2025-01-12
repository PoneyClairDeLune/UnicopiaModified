package com.minelittlepony.unicopia.entity.behaviour;

import com.minelittlepony.unicopia.entity.player.Pony;

import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseBaseEntity;

public class SteedBehaviour<T extends LivingEntity & JumpingMount> extends EntityBehaviour<T> {

    @Override
    public void update(Pony player, T entity, Disguise spell) {

        HorseBaseEntity horse = ((HorseBaseEntity)entity);

        boolean angry = !player.getEntity().isOnGround() && player.getMaster().isSprinting();
        boolean sneaking = isSneakingOnGround(player);

        angry |= sneaking;
        if (player.sneakingChanged() && sneaking) {
            horse.playAngrySound();
        }

        horse.setAngry(angry);
    }
}

package com.minelittlepony.unicopia.redux.ability;

import javax.annotation.Nullable;

import org.lwjgl.glfw.GLFW;

import com.minelittlepony.unicopia.core.Race;
import com.minelittlepony.unicopia.core.ability.Hit;
import com.minelittlepony.unicopia.core.ability.IPower;
import com.minelittlepony.unicopia.core.entity.player.IPlayer;
import com.minelittlepony.unicopia.redux.magic.spells.ChangelingTrapSpell;

public class PowerEngulf implements IPower<Hit> {

    @Override
    public String getKeyName() {
        return "engulf";
    }

    @Override
    public int getKeyCode() {
        return GLFW.GLFW_KEY_L;
    }

    @Override
    public int getWarmupTime(IPlayer player) {
        return 0;
    }

    @Override
    public int getCooldownTime(IPlayer player) {
        return 30;
    }

    @Override
    public boolean canUse(Race playerSpecies) {
        return playerSpecies == Race.CHANGELING;
    }

    @Nullable
    @Override
    public Hit tryActivate(IPlayer player) {
        return new Hit();
    }

    @Override
    public Class<Hit> getPackageType() {
        return Hit.class;
    }

    @Override
    public void apply(IPlayer player, Hit data) {
        new ChangelingTrapSpell().toss(player);
    }

    @Override
    public void preApply(IPlayer player) {

    }

    @Override
    public void postApply(IPlayer player) {

    }
}
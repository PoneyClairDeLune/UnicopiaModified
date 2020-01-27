package com.minelittlepony.unicopia.core.entity;

import com.minelittlepony.unicopia.core.Race;

/**
 * This interface is for any entities that are categorised as inanimated,
 * or part of a terrain effect.
 *
 * These typically can't be interacted with by players unless under certain cirumstances.
 *
 */
public interface InAnimate {
    boolean canInteract(Race race);
}
package com.minelittlepony.unicopia.core.entity;

import net.minecraft.entity.Entity;

/**
 * Generic container for an entity that has a race.
 *
 * @param <T> The type of owner
 */
public interface RaceContainer<T extends Entity> extends IEntity {

}
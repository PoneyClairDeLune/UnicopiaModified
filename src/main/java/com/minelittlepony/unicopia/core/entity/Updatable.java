package com.minelittlepony.unicopia.core.entity;

/**
 * Interface for objects that receive regular updates.
 */
@FunctionalInterface
public interface Updatable {
    /**
     * Called to update the internal logic.
     */
    void onUpdate();
}
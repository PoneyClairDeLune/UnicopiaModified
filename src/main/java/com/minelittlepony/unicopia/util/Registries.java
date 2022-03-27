package com.minelittlepony.unicopia.util;

import java.util.stream.Stream;

import com.mojang.serialization.Lifecycle;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.World;

public interface Registries {
    static <T> Registry<T> createSimple(Identifier id) {
        return FabricRegistryBuilder.from(new SimpleRegistry<T>(RegistryKey.ofRegistry(id), Lifecycle.stable(), null)).buildAndRegister();
    }

    static <T> RegistryEntryList<T> entriesForTag(World world, Tag<T> key) {
        return world.getRegistryManager().get(key.registry()).getOrCreateEntryList(key);
    }

    static <T> Stream<T> valuesForTag(World world, Tag<T> key) {
        return entriesForTag(world, key).stream().map(RegistryEntry::value);
    }
}

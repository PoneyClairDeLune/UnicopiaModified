package com.minelittlepony.unicopia;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.PersistentState;
import net.minecraft.world.dimension.DimensionType;

public class WorldTribeManager extends PersistentState {

    private Race defaultRace = Unicopia.getConfig().preferredRace.get();

    public WorldTribeManager() {}

    public WorldTribeManager(NbtCompound nbt) {
        defaultRace = Race.fromName(nbt.getString("defaultRace"));
    }

    public Race getDefaultRace() {
        return defaultRace;
    }

    public Race setDefaultRace(Race race) {
        return defaultRace = race;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        tag.putString("defaultRace", defaultRace.name());
        return tag;
    }

    public static String nameFor(RegistryEntry<DimensionType> dimension) {
        if (dimension.matchesKey(DimensionType.THE_END_REGISTRY_KEY)) {
            return "unicopia:tribes_end";
        }
        return "unicopia:tribes";
    }

    public static WorldTribeManager forWorld(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(WorldTribeManager::new, WorldTribeManager::new, nameFor(world.method_40134()));
    }
}

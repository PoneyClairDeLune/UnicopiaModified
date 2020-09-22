package com.minelittlepony.unicopia.ability.magic.spell;

import java.util.function.Supplier;

import com.minelittlepony.unicopia.ability.magic.Affinity;
import com.minelittlepony.unicopia.ability.magic.Caster;
import com.minelittlepony.unicopia.ability.magic.Spell;
import com.minelittlepony.unicopia.particle.MagicParticleEffect;

public class GenericSpell extends AbstractSpell {

    private final String name;

    private final int tint;

    private final Affinity affinity;

    static Supplier<Spell> factory(String name, int tint, Affinity affinity) {
        return () -> new GenericSpell(name, tint, affinity);
    }

    public GenericSpell(String name, int tint, Affinity affinity) {
        this.name = name;
        this.tint = tint;
        this.affinity = affinity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTint() {
        return tint;
    }

    @Override
    public boolean update(Caster<?> source) {
        return true;
    }

    @Override
    public void render(Caster<?> source) {
        source.spawnParticles(new MagicParticleEffect(getTint()), 1);
    }

    @Override
    public Affinity getAffinity() {
        return affinity;
    }
}
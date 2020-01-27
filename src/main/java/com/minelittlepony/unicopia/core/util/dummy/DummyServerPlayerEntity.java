package com.minelittlepony.unicopia.core.util.dummy;

import com.minelittlepony.unicopia.core.InteractionManager;
import com.minelittlepony.unicopia.core.entity.Owned;
import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class DummyServerPlayerEntity extends ServerPlayerEntity implements Owned<PlayerEntity> {

    private PlayerEntity owner;

    public DummyServerPlayerEntity(ServerWorld world, GameProfile profile) {
        super(world.getServer(), world, profile, new ServerPlayerInteractionManager(world));
    }

    @Override
    protected void onEquipStack(ItemStack stack) {
        /*noop*/
    }

    @Override
    public PlayerEntity getOwner() {
        return owner;
    }

    @Override
    public void setOwner(PlayerEntity owner) {
        this.owner = owner;
    }

    @Override
    public boolean shouldRenderName() {
        return !InteractionManager.instance().isClientPlayer(getOwner());
    }

    @Override
    public Text getDisplayName() {
        Text name = super.getDisplayName();
        name.getStyle().setItalic(true);
        return name;
    }
}
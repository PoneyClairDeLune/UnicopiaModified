package com.minelittlepony.unicopia.core.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import com.minelittlepony.unicopia.core.SpeciesList;
import com.minelittlepony.unicopia.core.entity.player.PlayerCamera;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(Camera.class)
public class MixinCamera {

    @Shadow
    private float pitch;
    @Shadow
    private float yaw;

    @Inject(method = "updateRotation()V", at = @At("HEAD"))
    private void updateRotation() {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            PlayerCamera view = SpeciesList.instance().getPlayer(player).getCamera();

            //event.setRoll(view.calculateRoll());
            pitch = view.calculatePitch(pitch);
            yaw = view.calculateYaw(yaw);
        }
    }
}
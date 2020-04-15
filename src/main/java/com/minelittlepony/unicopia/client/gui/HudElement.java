package com.minelittlepony.unicopia.client.gui;

import com.minelittlepony.unicopia.entity.player.Pony;

public interface HudElement {

    void repositionHud(UHud context);

    void renderHud(UHud context);

    boolean shouldRender(Pony player);
}
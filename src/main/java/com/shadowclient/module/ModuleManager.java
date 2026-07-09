package com.shadowclient.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {
    private static final List<ShadowModule> MODULES = new ArrayList<>();

    private ModuleManager() {
    }

    public static void init() {
        if (!MODULES.isEmpty()) return;

        MODULES.add(new ShadowModule("FPS Display", "HUD", "Lightweight FPS counter placeholder.", true));
        MODULES.add(new ShadowModule("Ping Display", "HUD", "Ping HUD planned with 2 second updates.", true));
        MODULES.add(new ShadowModule("CPS Display", "HUD", "Clicks per second HUD module.", false));
        MODULES.add(new ShadowModule("Keystrokes", "HUD", "Minimal keystrokes overlay module.", false));
        MODULES.add(new ShadowModule("Reach Display", "PvP Info", "Visual-only last hit distance display. Does not change reach.", true));
        MODULES.add(new ShadowModule("TierTagger", "PvP Info", "MCTiers / PvPTiers display framework.", false));
        MODULES.add(new ShadowModule("Menu Blur", "Visuals", "Simple menu blur placeholder.", true));
        MODULES.add(new ShadowModule("Motion Blur", "Visuals", "Optional visual-only motion blur placeholder.", false));
        MODULES.add(new ShadowModule("Armor HUD", "HUD", "Armor status HUD module.", false));
        MODULES.add(new ShadowModule("Potion HUD", "HUD", "Potion status HUD module.", false));
    }

    public static List<ShadowModule> modules() {
        return Collections.unmodifiableList(MODULES);
    }
}

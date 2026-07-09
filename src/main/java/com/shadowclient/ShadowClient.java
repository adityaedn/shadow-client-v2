package com.shadowclient;

import com.shadowclient.screen.ShadowModulesScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class ShadowClient implements ClientModInitializer {
    public static final String MOD_ID = "shadowclient";

    public static KeyBinding OPEN_MENU_KEY;

    public static final KeyBinding.Category SHADOW_CATEGORY =
            KeyBinding.Category.create(Identifier.of(MOD_ID, "category"));

    @Override
    public void onInitializeClient() {
        OPEN_MENU_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.shadowclient.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                SHADOW_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_MENU_KEY.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ShadowModulesScreen());
            }
        });
    }
}

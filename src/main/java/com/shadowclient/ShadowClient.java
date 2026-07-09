package com.shadowclient;

import com.shadowclient.module.ModuleManager;
import com.shadowclient.screen.ShadowModulesScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public final class ShadowClient implements ClientModInitializer {
    public static final String MOD_ID = "shadowclient";
    public static final Text NAME = Text.literal("Shadow Client");

    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        ModuleManager.init();

        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.shadowclient.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "key.category.shadowclient"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (!(mc.currentScreen instanceof ShadowModulesScreen)) {
                    mc.setScreen(new ShadowModulesScreen());
                }
            }
        });
    }
}

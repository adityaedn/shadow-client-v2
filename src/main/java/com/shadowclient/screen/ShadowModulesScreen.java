package com.shadowclient.screen;

import com.shadowclient.ShadowClient;
import com.shadowclient.module.ModuleManager;
import com.shadowclient.module.ShadowModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public final class ShadowModulesScreen extends Screen {
    private static final int PANEL_WIDTH = 430;
    private static final int PANEL_HEIGHT = 255;
    private static final int CARD_WIDTH = 128;
    private static final int CARD_HEIGHT = 42;
    private static final int GAP = 10;

    public ShadowModulesScreen() {
        super(ShadowClient.NAME);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int x = (this.width - PANEL_WIDTH) / 2;
        int y = (this.height - PANEL_HEIGHT) / 2;

        context.fill(x, y, x + PANEL_WIDTH, y + PANEL_HEIGHT, 0xCC101214);
        context.fill(x, y, x + PANEL_WIDTH, y + 28, 0xDD1A1D20);
        drawCentered(context, "SHADOW CLIENT", this.width / 2, y + 10, 0xFFE8E8E8);
        context.drawTextWithShadow(this.textRenderer, "Right Shift menu  |  Left click toggles modules  |  Esc closes", x + 16, y + 32, 0xFFAAAAAA);

        List<ShadowModule> modules = ModuleManager.modules();
        for (int i = 0; i < modules.size(); i++) {
            ShadowModule module = modules.get(i);
            int col = i % 3;
            int row = i / 3;
            int cardX = x + 16 + col * (CARD_WIDTH + GAP);
            int cardY = y + 55 + row * (CARD_HEIGHT + GAP);
            drawModuleCard(context, module, cardX, cardY, mouseX, mouseY);
        }

        context.drawTextWithShadow(this.textRenderer, "v0.1 clean base • Minecraft 1.21.11 • Fabric", x + 16, y + PANEL_HEIGHT - 18, 0xFF8E8E8E);
        super.render(context, mouseX, mouseY, delta);
    }

    private void drawModuleCard(DrawContext context, ShadowModule module, int x, int y, int mouseX, int mouseY) {
        boolean hover = mouseX >= x && mouseX <= x + CARD_WIDTH && mouseY >= y && mouseY <= y + CARD_HEIGHT;
        int bg = module.enabled() ? 0xDD30363B : 0xCC191C20;
        int border = module.enabled() ? 0xFF9FA4AA : 0xFF3A3D42;
        if (hover) {
            bg = module.enabled() ? 0xEE3A4047 : 0xDD25292E;
        }

        context.fill(x, y, x + CARD_WIDTH, y + CARD_HEIGHT, bg);
        context.fill(x, y, x + CARD_WIDTH, y + 1, border);
        context.fill(x, y + CARD_HEIGHT - 1, x + CARD_WIDTH, y + CARD_HEIGHT, border);
        context.fill(x, y, x + 1, y + CARD_HEIGHT, border);
        context.fill(x + CARD_WIDTH - 1, y, x + CARD_WIDTH, y + CARD_HEIGHT, border);

        context.drawTextWithShadow(this.textRenderer, module.name(), x + 8, y + 8, 0xFFFFFFFF);
        context.drawTextWithShadow(this.textRenderer, module.enabled() ? "ON" : "OFF", x + 8, y + 25, module.enabled() ? 0xFFBFC5CC : 0xFF777777);
    }

    private void drawCentered(DrawContext context, String text, int centerX, int y, int color) {
        int textWidth = this.textRenderer.getWidth(text);
        context.drawTextWithShadow(this.textRenderer, text, centerX - textWidth / 2, y, color);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int x = (this.width - PANEL_WIDTH) / 2;
            int y = (this.height - PANEL_HEIGHT) / 2;
            List<ShadowModule> modules = ModuleManager.modules();

            for (int i = 0; i < modules.size(); i++) {
                int col = i % 3;
                int row = i / 3;
                int cardX = x + 16 + col * (CARD_WIDTH + GAP);
                int cardY = y + 55 + row * (CARD_HEIGHT + GAP);

                if (mouseX >= cardX && mouseX <= cardX + CARD_WIDTH && mouseY >= cardY && mouseY <= cardY + CARD_HEIGHT) {
                    modules.get(i).toggle();
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (MinecraftClient.getInstance().options.inventoryKey.matchesKey(keyCode, scanCode)) {
            MinecraftClient.getInstance().setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}

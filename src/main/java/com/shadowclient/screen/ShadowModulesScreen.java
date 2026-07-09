package com.shadowclient.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ShadowModulesScreen extends Screen {

    private final String[] modules = {
            "FPS Display", "Ping Display", "CPS Display",
            "Reach Display", "Keystrokes", "TierTagger",
            "Menu Blur", "Motion Blur", "Armor HUD"
    };

    public ShadowModulesScreen() {
        super(Text.literal("Shadow Client"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        context.fill(0, 0, this.width, this.height, 0x77000000);

        int panelW = 360;
        int panelH = 230;
        int panelX = centerX - panelW / 2;
        int panelY = centerY - panelH / 2;

        context.fill(panelX, panelY, panelX + panelW, panelY + panelH, 0xEE101114);
        context.fill(panelX, panelY, panelX + panelW, panelY + 2, 0xFF8A8A8A);

        context.drawCenteredTextWithShadow(this.textRenderer, "SHADOW CLIENT", centerX, panelY + 16, 0xF2F2F2);
        context.drawCenteredTextWithShadow(this.textRenderer, "Right Shift Module Menu", centerX, panelY + 32, 0x9C9C9C);

        int startX = panelX + 22;
        int startY = panelY + 58;
        int cardW = 98;
        int cardH = 38;
        int gap = 12;

        for (int i = 0; i < modules.length; i++) {
            int col = i % 3;
            int row = i / 3;

            int x = startX + col * (cardW + gap);
            int y = startY + row * (cardH + gap);

            boolean enabled = i < 4;

            int bg = enabled ? 0xFF24262B : 0xFF17181C;
            int border = enabled ? 0xFF777777 : 0xFF3A3A3A;
            int text = enabled ? 0xFFFFFFFF : 0xFF9A9A9A;
            int status = enabled ? 0xFFBDBDBD : 0xFF666666;

            context.fill(x, y, x + cardW, y + cardH, bg);
            context.fill(x, y, x + cardW, y + 1, border);
            context.fill(x, y + cardH - 1, x + cardW, y + cardH, border);
            context.fill(x, y, x + 1, y + cardH, border);
            context.fill(x + cardW - 1, y, x + cardW, y + cardH, border);

            context.drawTextWithShadow(this.textRenderer, modules[i], x + 8, y + 8, text);
            context.drawTextWithShadow(this.textRenderer, enabled ? "ON" : "OFF", x + 8, y + 22, status);
        }

        context.drawCenteredTextWithShadow(this.textRenderer, "ESC to close  |  V2 Foundation", centerX, panelY + panelH - 18, 0x777777);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}

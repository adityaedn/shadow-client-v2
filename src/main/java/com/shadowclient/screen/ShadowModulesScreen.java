package com.shadowclient.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ShadowModulesScreen extends Screen {

    public ShadowModulesScreen() {
        super(Text.literal("Shadow Client"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        context.fill(0, 0, this.width, this.height, 0x66000000);

        context.fill(centerX - 140, centerY - 90, centerX + 140, centerY + 90, 0xDD111111);
        context.drawCenteredTextWithShadow(this.textRenderer, "Shadow Client", centerX, centerY - 72, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "Right Shift Menu", centerX, centerY - 50, 0xAAAAAA);

        context.drawTextWithShadow(this.textRenderer, "Modules:", centerX - 115, centerY - 20, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, "- FPS Display", centerX - 100, centerY, 0xCCCCCC);
        context.drawTextWithShadow(this.textRenderer, "- Ping Display", centerX - 100, centerY + 15, 0xCCCCCC);
        context.drawTextWithShadow(this.textRenderer, "- Reach Display", centerX - 100, centerY + 30, 0xCCCCCC);
        context.drawTextWithShadow(this.textRenderer, "- TierTagger", centerX - 100, centerY + 45, 0xCCCCCC);

        context.drawCenteredTextWithShadow(this.textRenderer, "Press ESC to close", centerX, centerY + 68, 0x777777);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}

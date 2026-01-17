package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.item.LightTooltip;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;

public class ClientLightTooltip implements ClientTooltipComponent {
    @SuppressWarnings("deprecation")
    private static final ResourceLocation TEXTURE = new ResourceLocation("caerula_arbor", "textures/screens/light.png");
    private final String text;

    public ClientLightTooltip(LightTooltip tooltip) {
        this.text = tooltip.text();
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth(Font font) {
        return 12 + font.width(this.text);
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        // Texture is 64x32.
        // We want a 32x32 icon from the center.
        // Center of 64x32 is (32, 16).
        // Top-left of 32x32 box centered at (32, 16) is (32 - 16, 16 - 16) = (16, 0).
        // Draw 8x8 icon on screen, from 32x32 source
        guiGraphics.blit(TEXTURE, x, y, 8, 8, 16, 0, 32, 32, 64, 32);

        int color = 0x55FF55; // Default Green (Increase)
        if (this.text.startsWith("-")) {
            color = 0xFF5555; // Red (Decrease)
        }

        guiGraphics.drawString(font, this.text, x + 12, y, color, false);
    }
}


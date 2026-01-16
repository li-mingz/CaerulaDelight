package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.item.SanityTooltip;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;

public class ClientSanityTooltip implements ClientTooltipComponent {
    @SuppressWarnings("deprecation")
    private static final ResourceLocation TEXTURE = new ResourceLocation("caerula_arbor", "textures/screens/sanity.png");
    private final String text;

    public ClientSanityTooltip(SanityTooltip tooltip) {
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
        // Texture is 320x16. Last icon starts at 304 (320 - 16).
        // Draw 10x10 icon on screen, from 16x16 source
        guiGraphics.blit(TEXTURE, x, y, 8, 8, 304, 0, 16, 16, 320, 16);
        guiGraphics.drawString(font, this.text, x + 12, y, 0xFF5555, false);
    }
}


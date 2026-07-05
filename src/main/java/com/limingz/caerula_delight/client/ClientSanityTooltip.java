package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.item.SanityTooltip;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;

public class ClientSanityTooltip implements ClientTooltipComponent {
    @SuppressWarnings("deprecation")
    private static final ResourceLocation TEXTURE = new ResourceLocation("caerula_arbor", "textures/screens/sanity_icon.png");
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
        // sanity_icon.png is 16x16; draw 8x8 icon on screen
        guiGraphics.blit(TEXTURE, x, y, 8, 8, 0, 0, 16, 16, 16, 16);
        guiGraphics.drawString(font, this.text, x + 12, y, 0xFF5555, false);
    }
}


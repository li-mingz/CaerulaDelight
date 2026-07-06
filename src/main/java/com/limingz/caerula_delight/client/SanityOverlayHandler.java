package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.util.SanityCalculator;
import com.limingz.caerula_delight.util.SanityEffect;
import com.limingz.caerula_delight.registry.SanityFoodRegistry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.mcreator.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考 AppleSkin，在玩家手持改变 sanity 的食物时，
 * 于 Caerula Arbor 的神经损伤条上叠加预览：
 * - 扣除 sanity：红色闪烁，透明度 0 ~ 0.85
 * - 恢复 sanity：青绿色闪烁，透明度 0 ~ 0.85
 *
 * <p>支持多阶段效果（如先扣后回、先加后扣）。
 * 最终净方向作为主层绘制，反向阶段半透明叠加上去，
 * 避免同区域完全覆盖导致只能看到一种颜色。</p>
 */
@Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SanityOverlayHandler {

    private static final ResourceLocation SANITY_BAR =
            new ResourceLocation("caerula_arbor", "textures/screens/sanity_player_bar.png");

    private static final int FRAME_V = 4;
    private static final int FILL_V = 0;
    private static final int FILL_OFFSET_X = 10;
    private static final int FILL_OFFSET_Y = 3;
    private static final int FILL_MAX_WIDTH = 50;
    private static final int FRAME_WIDTH = 62;
    private static final int FRAME_HEIGHT = 8;
    private static final int FILL_HEIGHT = 4;
    private static final int TEXTURE_WIDTH = 62;
    private static final int TEXTURE_HEIGHT = 12;
    private static final double MAX_SANITY = SanityCalculator.MAX_SANITY;

    private static float flashAlpha = 0f;
    private static float unclampedFlashAlpha = 0f;
    private static int alphaDir = 1;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        updateFlash();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRenderGuiOverlay(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) {
            resetFlash();
            return;
        }

        ItemStack heldItem = getHeldSanityItem(player);
        if (heldItem.isEmpty()) {
            resetFlash();
            return;
        }

        List<SanityEffect> effects = SanityFoodRegistry.getEffects(heldItem);
        if (effects.isEmpty()) {
            resetFlash();
            return;
        }

        renderSanityPreview(event.getGuiGraphics(), player, effects);
    }

    private static void renderSanityPreview(GuiGraphics guiGraphics, Player player, List<SanityEffect> effects) {
        int w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int h = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        int dx = Math.toIntExact(Math.round((Double) CaerulaConfigsConfiguration.X_OFFSET.get()));
        int dy = Math.toIntExact(Math.round((Double) CaerulaConfigsConfiguration.Y_OFFSET.get()));

        int x = w / 2 + 93 + dx;
        int y = h - 12 + dy;
        int fillX = x + FILL_OFFSET_X;
        int fillY = y + FILL_OFFSET_Y;

        double currentSanity = SanityCalculator.getCurrentSanity(player);
        double projectedSanity = SanityCalculator.predictSanityAfterEffects(player, effects);

        // sanity 满时原模组不绘制 bar，这里补画底框和当前填充
        boolean barVisibleByMod = currentSanity < MAX_SANITY;
        if (!barVisibleByMod) {
            guiGraphics.blit(SANITY_BAR, x, y, 0.0F, FRAME_V, FRAME_WIDTH, FRAME_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);
            int currentWidth = (int) (FILL_MAX_WIDTH * (currentSanity / MAX_SANITY));
            if (currentWidth > 0) {
                guiGraphics.blit(SANITY_BAR, fillX, fillY, 0.0F, FILL_V, currentWidth, FILL_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);
            }
        }

        int currentWidth = (int) (FILL_MAX_WIDTH * (currentSanity / MAX_SANITY));
        int projectedWidth = (int) (FILL_MAX_WIDTH * (projectedSanity / MAX_SANITY));

        if (Math.abs(projectedWidth - currentWidth) > 0) {
            List<Segment> segments = computeSegments(player, effects, currentSanity, currentWidth);
            double netDelta = effects.stream().mapToDouble(SanityEffect::delta).sum();
            boolean netGain = netDelta > 0.0;

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            // 最终净方向作为“主层”先画且更实；反向阶段半透明叠在上面，
            // 这样同区域重叠时两层都能看见，最终颜色也由净方向决定。
            float mainAlpha = 0.85F * flashAlpha;
            float subAlpha = 0.45F * flashAlpha;

            for (Segment seg : segments) {
                if ((seg.delta > 0.0) == netGain) {
                    drawSegment(guiGraphics, fillX, fillY, seg, mainAlpha);
                }
            }
            for (Segment seg : segments) {
                if ((seg.delta > 0.0) != netGain) {
                    drawSegment(guiGraphics, fillX, fillY, seg, subAlpha);
                }
            }

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
        }
    }

    private static List<Segment> computeSegments(Player player, List<SanityEffect> effects, double startSanity, int startWidth) {
        List<Segment> segments = new ArrayList<>();
        double runningSanity = startSanity;
        int runningWidth = startWidth;
        for (SanityEffect effect : effects) {
            double nextSanity = SanityCalculator.applyRawDelta(player, runningSanity, effect.delta());
            int nextWidth = (int) (FILL_MAX_WIDTH * (nextSanity / MAX_SANITY));
            segments.add(new Segment(effect.delta(), runningWidth, nextWidth));
            runningSanity = nextSanity;
            runningWidth = nextWidth;
        }
        return segments;
    }

    private static void drawSegment(GuiGraphics guiGraphics, int fillX, int fillY, Segment seg, float alpha) {
        int segmentWidth = Math.abs(seg.toWidth - seg.fromWidth);
        if (segmentWidth <= 0) {
            return;
        }
        boolean isLoss = seg.delta < 0.0;
        int segmentX = fillX + Math.min(seg.fromWidth, seg.toWidth);
        float u = (float) Math.min(seg.fromWidth, seg.toWidth);

        RenderSystem.setShaderColor(
                isLoss ? 1.0F : 0.0F,
                isLoss ? 0.15F : 1.0F,
                isLoss ? 0.15F : 0.6F,
                alpha
        );

        guiGraphics.blit(
                SANITY_BAR,
                segmentX,
                fillY,
                u,
                FILL_V,
                segmentWidth,
                FILL_HEIGHT,
                TEXTURE_WIDTH,
                TEXTURE_HEIGHT
        );
    }

    private record Segment(double delta, int fromWidth, int toWidth) {
    }

    private static ItemStack getHeldSanityItem(Player player) {
        ItemStack main = player.getMainHandItem();
        if (!main.isEmpty() && SanityFoodRegistry.getDelta(main) != null) {
            return main;
        }
        ItemStack off = player.getOffhandItem();
        if (!off.isEmpty() && SanityFoodRegistry.getDelta(off) != null) {
            return off;
        }
        return ItemStack.EMPTY;
    }

    private static void updateFlash() {
        unclampedFlashAlpha += alphaDir * 0.125f;
        if (unclampedFlashAlpha >= 1.5f) {
            alphaDir = -1;
        } else if (unclampedFlashAlpha <= -0.5f) {
            alphaDir = 1;
        }
        flashAlpha = Math.max(0F, Math.min(1F, unclampedFlashAlpha));
    }

    private static void resetFlash() {
        unclampedFlashAlpha = 0f;
        flashAlpha = 0f;
        alphaDir = 1;
    }
}

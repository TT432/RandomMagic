package com.nmmoc7.randommagic.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nmmoc7.phoenixlib.client.gif.TextureRenderType;
import com.nmmoc7.phoenixlib.client.gif.TextureRenderer;
import com.nmmoc7.phoenixlib.client.gif.TexturesUtils;
import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.capability.CapabilityMana;
import com.nmmoc7.randommagic.util.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ManaRenderer {
    public static final Lazy<TextureRenderType> MANA100 = getRenderType(new ResourceLocation(RandomMagic.MOD_ID, "gui/mana100.png"));
    public static final Lazy<TextureRenderType> MANA50 = getRenderType(new ResourceLocation(RandomMagic.MOD_ID, "gui/mana50.png"));
    public static final Lazy<TextureRenderType> MANA20 = getRenderType(new ResourceLocation(RandomMagic.MOD_ID, "gui/mana20.png"));

    private static Lazy<TextureRenderType> getRenderType(ResourceLocation resourceLocation) {
        return Lazy.of(() -> {
            try {
                return TexturesUtils.getPngRenderType(resourceLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        });
    }

    @SubscribeEvent
    public static void onRenderHotBar(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft mc = Minecraft.getInstance();

            if (mc.getRenderViewEntity() instanceof PlayerEntity) {
                int x = event.getWindow().getScaledWidth() / 2 - 60;
                int y = event.getWindow().getScaledHeight() - 24;

                MatrixStack matrixStack = event.getMatrixStack();

                CapabilityMana cap = CapabilityMana.getCapability(mc.player);

                if (cap != null) {
                    int mana = cap.getMana();
                    int maxMana = cap.getMaxMana();
                    int p = getManaP(mana, maxMana);

                    if (p > 80) {
                        renderTexture(MANA100.get(), matrixStack, x, y, 0xFFFFFFFF);
                    } else if (p > 50) {
                        Vector3i a = getAlpha((p - 50F) / 30);
                        renderTexture(MANA100.get(), matrixStack, x, y, a.getX() | 0xFFFFFF);
                        renderTexture(MANA50.get(), matrixStack, x, y, a.getY() | 0xFFFFFF);
                    } else {
                        Vector3i a = getAlpha(p / 20F);
                        renderTexture(MANA50.get(), matrixStack, x, y, a.getX() | 0xFFFFFF);
                        renderTexture(MANA20.get(), matrixStack, x, y, a.getY() | 0xFFFFFF);
                    }
                }
            }
        }
    }

    public static int getManaP(int mana, int maxMana) {
        if (mana == 0 || maxMana == 0) {
            return 0;
        }
        else {
            return (int) (((float) mana / (float) maxMana) * 100);
        }
    }

    public static Vector3i getAlpha(float alpha) {
        int a = (int) (alpha * 255);
        return new Vector3i(a * 0xFF_000000, ((255 - a) / 255) * 0xFF_000000, 0);
    }

    public static void renderTexture(TextureRenderType type, MatrixStack matrixStack, int x1, int y1, int color) {
        matrixStack.push();

        TextureRenderer.render(type,
                Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(),
                matrixStack.getLast().getMatrix(),
                color,
                MathUtils.MAX_LIGHT
        );
        matrixStack.pop();
    }
}

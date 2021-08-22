package com.nmmoc7.randommagic.magic_circle.gif;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class ModRenderType extends RenderType {
    ModRenderType(int texture) {
        super("gif_render", DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP,
                GL11.GL_QUADS, 256, false, true,
                () -> {
                    GENERAL_STATES.forEach(RenderState::setupRenderState);
                    RenderSystem.enableTexture();
                    RenderSystem.bindTexture(texture);
                },
                () -> GENERAL_STATES.forEach(RenderState::clearRenderState));
    }

    private static final ImmutableList<RenderState> GENERAL_STATES = ImmutableList.of(
            RenderState.TRANSLUCENT_TRANSPARENCY,
            RenderState.DIFFUSE_LIGHTING_DISABLED,
            RenderState.SHADE_DISABLED,
            RenderState.DEFAULT_ALPHA,
            RenderState.DEPTH_LEQUAL,
            RenderState.CULL_ENABLED,
            RenderState.LIGHTMAP_ENABLED,
            RenderState.OVERLAY_DISABLED,
            RenderState.FOG,
            RenderState.NO_LAYERING,
            RenderState.MAIN_TARGET,
            RenderState.DEFAULT_TEXTURING,
            RenderState.COLOR_DEPTH_WRITE,
            RenderState.DEFAULT_LINE
    );
}

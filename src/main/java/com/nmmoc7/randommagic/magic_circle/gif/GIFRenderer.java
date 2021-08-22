package com.nmmoc7.randommagic.magic_circle.gif;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Matrix4f;

public class GIFRenderer {
    public static void render(ModRenderType modRenderType, IRenderTypeBuffer source, Matrix4f matrix, int color, int light) {
            int alpha = color >>> 24;
            if (alpha > 0) {
                int red = (color >> 16) & 255, green = (color >> 8) & 255, blue = color & 255;
                final IVertexBuilder builder = source.getBuffer(modRenderType);
                // Vertex format Pos -> Color -> Tex -> Light -> End.

                builder.pos(matrix, 0F, 1F / 256F, 1F)
                        .color(red, green, blue, alpha).tex(0F, 1F).lightmap(light).endVertex();
                builder.pos(matrix, 1F, 1F / 256F, 1F)
                        .color(red, green, blue, alpha).tex(1F, 1F).lightmap(light).endVertex();
                builder.pos(matrix, 1F, 1F / 256F, 0F)
                        .color(red, green, blue, alpha).tex(1F, 0F).lightmap(light).endVertex();
                builder.pos(matrix, 0F, 1F / 256F, 0F)
                        .color(red, green, blue, alpha).tex(0F, 0F).lightmap(light).endVertex();

                builder.pos(matrix, 0F, -1F / 256F, 0F)
                        .color(red, green, blue, alpha).tex(0F, 0F).lightmap(light).endVertex();
                builder.pos(matrix, 1F, -1F / 256F, 0F)
                        .color(red, green, blue, alpha).tex(1F, 0F).lightmap(light).endVertex();
                builder.pos(matrix, 1F, -1F / 256F, 1F)
                        .color(red, green, blue, alpha).tex(1F, 1F).lightmap(light).endVertex();
                builder.pos(matrix, 0F, -1F / 256F, 1F)
                        .color(red, green, blue, alpha).tex(0F, 1F).lightmap(light).endVertex();
        }
    }
}

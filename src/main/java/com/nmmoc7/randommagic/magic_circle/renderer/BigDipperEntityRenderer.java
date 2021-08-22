package com.nmmoc7.randommagic.magic_circle.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.magic_circle.entity.BigDipperEntity;
import com.nmmoc7.randommagic.magic_circle.renderer.model.BigDipperEntityModel;
import com.nmmoc7.randommagic.util.MathUtils;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 * @author DustW
 */
public class BigDipperEntityRenderer extends EntityRenderer<BigDipperEntity> {
    public final BigDipperEntityModel MODEL = new BigDipperEntityModel();

    protected BigDipperEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public boolean shouldRender(BigDipperEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public void render(BigDipperEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (MODEL.entity == null) {
            MODEL.entity = entityIn;
        }

        matrixStackIn.push();
        matrixStackIn.scale(-9, -1, 9);
        matrixStackIn.translate(0, -1.6, 0);

        MODEL.partTick = partialTicks;
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityTranslucent(this.getEntityTexture(entityIn)));
        MODEL.render(matrixStackIn, ivertexbuilder, MathUtils.LIGHT_12, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

        matrixStackIn.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(BigDipperEntity entity) {
        return new ResourceLocation(RandomMagic.MOD_ID, "textures/entity/big_dipper.png");
    }
}

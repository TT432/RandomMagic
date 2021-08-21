package com.nmmoc7.randommagic.magic_circle.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.magic_circle.entity.BigDipperEntity;
import com.nmmoc7.randommagic.magic_circle.renderer.model.BigDipperEntityModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityTranslucent(this.getEntityTexture(entityIn)));
        MODEL.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    @Override
    public ResourceLocation getEntityTexture(BigDipperEntity entity) {
        return new ResourceLocation(RandomMagic.MOD_ID, "textures/entity/big_dipper.png");
    }
}

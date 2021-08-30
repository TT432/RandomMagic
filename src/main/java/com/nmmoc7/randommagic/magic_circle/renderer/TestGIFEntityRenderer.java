package com.nmmoc7.randommagic.magic_circle.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.nmmoc7.phoenixlib.client.gif.GIFInstance;
import com.nmmoc7.phoenixlib.client.gif.TextureRenderer;
import com.nmmoc7.randommagic.TickCounterHandler;
import com.nmmoc7.randommagic.magic_circle.entity.TestGIFEntity;
import com.nmmoc7.randommagic.util.MathUtils;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class TestGIFEntityRenderer extends EntityRenderer<TestGIFEntity> {
    protected TestGIFEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public boolean shouldRender(TestGIFEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public void render(TestGIFEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.scale(-30, -1, 30);
        GIFInstance testGif = GIFRegisterHandler.TEST.get();
        TextureRenderer.render(testGif.getFrame(getTexture(partialTicks, testGif.maxFrame)),
                bufferIn, matrixStackIn.getLast().getMatrix(), 0xFFFFFFFF, MathUtils.MAX_LIGHT);
        matrixStackIn.pop();
    }

    public int getTexture(float partTick, int maxFrame) {
        return MathHelper.fastFloor(((TickCounterHandler.tick + partTick) / 20) % maxFrame);
    }

    @Override
    public ResourceLocation getEntityTexture(TestGIFEntity entity) {
        return new ResourceLocation("");
    }
}

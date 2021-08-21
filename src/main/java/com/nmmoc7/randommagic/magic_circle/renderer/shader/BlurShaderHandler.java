package com.nmmoc7.randommagic.magic_circle.renderer.shader;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.util.VarLazy;
import com.sun.org.apache.regexp.internal.RE;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

/**
 * @author DustW
 */
public class BlurShaderHandler {
    public static final BlurShaderHandler INSTANCE = new BlurShaderHandler();

    private BlurShaderHandler() {
    }

    Minecraft mc = Minecraft.getInstance();
    MainWindow mainWindow = mc.getMainWindow();

    public int getWidth() {
        return mainWindow.getFramebufferWidth();
    }

    public int getHeight() {
        return mainWindow.getFramebufferHeight();
    }

    VarLazy<Framebuffer> framebuffer = new VarLazy<>(() ->
            new Framebuffer(getWidth(), getHeight(), false, Minecraft.IS_RUNNING_ON_MAC));

    public static final ResourceLocation TEXTURE = new ResourceLocation(RandomMagic.MOD_ID, "textures/entity/big_dipper.png");

    public void resize() {
        ShaderPrograms.LOADER.get().createBindFramebuffers(getWidth(), getHeight());
    }

    public void v(MatrixStack matrixStack, BufferBuilder bufferBuilder, ModelRenderer model, int packedLight, int packedOverlay) {
        matrixStack.push();

        mc.getTextureManager().bindTexture(TEXTURE);
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP);

        matrixStack.scale(3, -1, 3);
        matrixStack.translate(0, -1.6, 0);

        model.render(matrixStack, bufferBuilder, packedLight, packedOverlay);

        bufferBuilder.finishDrawing();

        matrixStack.pop();
    }

    public void init(MatrixStack matrixStack, BufferBuilder bufferBuilder, ModelRenderer model, int packedLight, int packedOverlay) {
        resize();
        v(matrixStack, bufferBuilder, model, packedLight, packedOverlay);
        Framebuffer framebufferTemp = ShaderPrograms.LOADER.get().getFramebufferRaw(new ResourceLocation(RandomMagic.MOD_ID, "final"));
        framebufferTemp.framebufferClear(Minecraft.IS_RUNNING_ON_MAC);
        framebufferTemp.bindFramebuffer(false);
        framebuffer.set(framebufferTemp);
    }

    public void renderA(BufferBuilder bufferBuilder) {
        RenderSystem.depthMask(true);
        WorldVertexBufferUploader.draw(bufferBuilder);
    }

    public void renderB(float partialTick) {
        inBlurShader(true);
        ShaderPrograms.LOADER.get().render(partialTick);
        inBlurShader(false);
        ShaderPrograms.LOADER.get().render(partialTick);
    }

    private void inBlurShader(boolean horizontal) {
        ShaderPrograms.BLUR.get().uniformBool("horizontal", horizontal);
    }

    public void renderC() {
        mc.getFramebuffer().bindFramebuffer(false);
    }

    public void renderD() {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
        framebuffer.get().framebufferRenderExt(getWidth(), getHeight(), false);
    }

    public void renderE() {
        RenderSystem.disableBlend();
        RenderSystem.depthMask(false);
    }

    public Vector3d getView() {
        return mc.gameRenderer.getActiveRenderInfo().getProjectedView();
    }
}

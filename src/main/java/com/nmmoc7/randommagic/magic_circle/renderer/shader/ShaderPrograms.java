package com.nmmoc7.randommagic.magic_circle.renderer.shader;

import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.util.VarLazy;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Lazy;

public class ShaderPrograms {
    static final Minecraft mc = Minecraft.getInstance();
    static final MainWindow mainWin = mc.getMainWindow();
    static final int width = mainWin.getFramebufferWidth();
    static final int height = mainWin.getFramebufferHeight();

    public static final VarLazy<ShaderLoader> LOADER = new VarLazy<>(() -> new ShaderLoader(
            Minecraft.getInstance().getResourceManager(),
            Minecraft.getInstance().getFramebuffer()));

    public static final Framebuffer FINAL =
            LOADER.get().addFramebuffer(new ResourceLocation(RandomMagic.MOD_ID, "final"), width, height);

    public static final Framebuffer SWAP =
            LOADER.get().addFramebuffer(new ResourceLocation(RandomMagic.MOD_ID, "swap"), width, height);

    public static final Lazy<ShaderProgram> BLUR = Lazy.of(() -> new ShaderProgram(
            ShaderUtils.getShaderFile(new ResourceLocation(RandomMagic.MOD_ID, "shaders/blur.fsh")),
            ShaderUtils.getShaderFile(new ResourceLocation(RandomMagic.MOD_ID, "shaders/blur.vsh"))));

    public static final ShaderProgram BLUR_A =
            LOADER.get().addShaderProgram(BLUR.get(), SWAP, FINAL);

    public static final ShaderProgram BLUR_B =
            LOADER.get().addShaderProgram(BLUR.get(), FINAL, SWAP);
}

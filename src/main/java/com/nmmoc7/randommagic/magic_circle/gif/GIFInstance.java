package com.nmmoc7.randommagic.magic_circle.gif;

import com.sun.imageio.plugins.gif.GIFImageReader;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GIFInstance {
    public final int maxFrame;
    ResourceLocation file;
    int[] frameList;

    public GIFInstance(ResourceLocation file) {
        this.file = file;
        maxFrame = getMaxFrame();
        frameList = new int[maxFrame];

        GIFImageReader reader = (GIFImageReader) ImageIO.getImageReadersByFormatName("gif").next();
        ImageInputStream in = GIFUtils.getGIFFile(file);
        reader.setInput(in);

        for (int i = 0; i < maxFrame; i++) {
            try {
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageOutputStream iOut = ImageIO.createImageOutputStream(bs);
                ImageIO.write(reader.read(i), "png", iOut);
                frameList[i] = loadTexture(bs.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ModRenderType make(int frame) {
        return new ModRenderType(frameList[frame]);
    }

    private Integer getMaxFrame() {
        try {
            GIFImageReader reader = (GIFImageReader) ImageIO.getImageReadersByFormatName("gif").next();
            ImageInputStream in = GIFUtils.getGIFFile(file);
            reader.setInput(in, false, false);
            int maxFrame = reader.getNumImages(true);
            in.close();
            return maxFrame;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private int loadTexture(byte[] frame) throws IOException {
        NativeImage image = NativeImage.read(new ByteArrayInputStream(frame));

        int texture = TextureUtil.generateTextureId();

        TextureUtil.prepareImage(NativeImage.PixelFormatGLCode.RGBA,
                texture, 2, image.getWidth(), image.getHeight());

        image.uploadTextureSub(0, 0, 0,
                0, 0,
                image.getWidth(), image.getHeight(),
                true, true);

        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        return texture;
    }
}

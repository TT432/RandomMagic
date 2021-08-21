package com.nmmoc7.randommagic.magic_circle.renderer.shader;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author DustW
 */
public class ShaderUtils {
    public static String getShaderFile(ResourceLocation file) {
        IResourceManager a = Minecraft.getInstance().getResourceManager();
        try {
            IResource b = a.getResource(file);
            InputStream c = b.getInputStream();
            return IOUtils.toString(c, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

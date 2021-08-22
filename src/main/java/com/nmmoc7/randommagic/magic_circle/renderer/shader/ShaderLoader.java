package com.nmmoc7.randommagic.magic_circle.renderer.shader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;

import java.util.List;
import java.util.Map;

public class ShaderLoader {
    public final Framebuffer mainFramebuffer;
    public final List<Framebuffer> listFramebuffers = Lists.newArrayList();
    public final Map<ResourceLocation, Framebuffer> mapFramebuffers = Maps.newHashMap();
    public int mainFramebufferWidth;
    public int mainFramebufferHeight;
    public Matrix4f projectionMatrix;
    public final IResourceManager resourceManager;
    public float time;
    public float lastStamp;

    public ShaderLoader(IResourceManager resourceManagerIn, Framebuffer mainFramebufferIn) throws JsonSyntaxException {
        this.resourceManager = resourceManagerIn;
        this.mainFramebuffer = mainFramebufferIn;
        this.time = 0.0F;
        this.lastStamp = 0.0F;
        this.mainFramebufferWidth = mainFramebufferIn.framebufferWidth;
        this.mainFramebufferHeight = mainFramebufferIn.framebufferHeight;
        this.resetProjectionMatrix();
    }

    public void render(float partialTicks) {
        if (partialTicks < this.lastStamp) {
            this.time += 1.0F - this.lastStamp;
            this.time += partialTicks;
        } else {
            this.time += partialTicks - this.lastStamp;
        }

        this.time = time % 20.0F;
        this.lastStamp = partialTicks;

        for(ShaderProgram shaderProgram: listShaderProgram) {
            shaderProgram.render();
        }

    }

    public Framebuffer getFramebufferRaw(ResourceLocation attributeName) {
        return this.mapFramebuffers.get(attributeName);
    }

    List<ShaderProgram> listShaderProgram = Lists.newArrayList();

    public ShaderProgram addShaderProgram(ShaderProgram shaderProgram, Framebuffer framebufferIn, Framebuffer framebufferOut) {
        shaderProgram.init(framebufferIn, framebufferOut);
        listShaderProgram.add(this.listShaderProgram.size(), shaderProgram);
        return shaderProgram;
    }

    private void resetProjectionMatrix() {
        this.projectionMatrix = Matrix4f.orthographic((float)this.mainFramebuffer.framebufferTextureWidth, (float)this.mainFramebuffer.framebufferTextureHeight, 0.1F, 1000.0F);
    }

    public Framebuffer addFramebuffer(ResourceLocation name, int width, int height) {
        Framebuffer framebuffer = new Framebuffer(width, height, true, Minecraft.IS_RUNNING_ON_MAC);
        framebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
        this.mapFramebuffers.put(name, framebuffer);
        if (width == this.mainFramebufferWidth && height == this.mainFramebufferHeight) {
            this.listFramebuffers.add(framebuffer);
        }
        return framebuffer;
    }

    public void createBindFramebuffers(int width, int height) {
        this.mainFramebufferWidth = this.mainFramebuffer.framebufferTextureWidth;
        this.mainFramebufferHeight = this.mainFramebuffer.framebufferTextureHeight;
        this.resetProjectionMatrix();

        for(ShaderProgram shaderProgram: listShaderProgram) {
            // todo shaderProgram.setProjectionMatrix(this.projectionMatrix);
        }

        for(Framebuffer framebuffer : this.listFramebuffers) {
            framebuffer.resize(width, height, Minecraft.IS_RUNNING_ON_MAC);
        }

    }
}

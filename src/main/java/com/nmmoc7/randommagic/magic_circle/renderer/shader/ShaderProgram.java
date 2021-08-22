package com.nmmoc7.randommagic.magic_circle.renderer.shader;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.math.vector.Matrix4f;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

/**
 * @author MaxSeth
 */
public class ShaderProgram {
    public Framebuffer framebufferIn;
    public Framebuffer framebufferOut;

    private final int fragmentShader;
    private final int vertexShader;
    private final int shaderProgram;
    private boolean isUse;

    public void init(Framebuffer framebufferInIn, Framebuffer framebufferOutIn) {
        this.framebufferIn = framebufferInIn;
        this.framebufferOut = framebufferOutIn;
    }

    public void render() {
        this.framebufferIn.unbindFramebuffer();
        float f = (float)this.framebufferOut.framebufferTextureWidth;
        float f1 = (float)this.framebufferOut.framebufferTextureHeight;
        RenderSystem.viewport(0, 0, (int)f, (int)f1);

        Minecraft minecraft = Minecraft.getInstance();
        this.framebufferOut.framebufferClear(Minecraft.IS_RUNNING_ON_MAC);
        this.framebufferOut.bindFramebuffer(false);
        RenderSystem.depthFunc(519);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(0.0D, 0.0D, 500.0D).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(f, 0.0D, 500.0D).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(f, f1, 500.0D).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.0D, f1, 500.0D).color(255, 255, 255, 255).endVertex();
        bufferbuilder.finishDrawing();
        WorldVertexBufferUploader.draw(bufferbuilder);
        RenderSystem.depthFunc(515);

        this.framebufferOut.unbindFramebuffer();
        this.framebufferIn.unbindFramebufferTexture();
    }

    /**
     * 创建着色器程序
     *
     * @param frag 片元着色器内容
     * @param vert 顶点着色器内容
     */
    public ShaderProgram(String frag, String vert) {
        fragmentShader = createShader(GL20.GL_FRAGMENT_SHADER, frag);
        vertexShader = createShader(GL20.GL_VERTEX_SHADER, vert);
        shaderProgram = createProgram();
    }

    private boolean checkShader(int shader) {
        int status = GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS);
        if (status == 0) {
            String error = GL20.glGetShaderInfoLog(shader, 512);
            //TODO Replace this by logger
            System.out.println("SHADER COMPILE ERROR:" + error);
            return false;
        }
        return true;
    }

    private int createShader(int type, String source) {
        int shader = GlStateManager.createShader(type);
        GL20.glShaderSource(shader, source);
        GL20.glCompileShader(shader);
        checkShader(shader);
        return shader;
    }

    private int createProgram() {
        int program = GL20.glCreateProgram();
        GL20.glAttachShader(program, fragmentShader);
        GL20.glAttachShader(program, vertexShader);
        GL20.glLinkProgram(program);
        checkProgram(program);
        return program;
    }

    private boolean checkProgram(int program) {
        int status = GL20.glGetProgrami(program, GL20.GL_LINK_STATUS);
        if (status == 0) {
            String error = GL20.glGetProgramInfoLog(program, 512);
            //TODO Replace this by logger
            System.out.println("PROGRAM LINK ERROR:" + error);
            return false;
        }
        return true;
    }

    public void useShader() {
        if (!isUse) {
            GL20.glUseProgram(shaderProgram);
            isUse = true;
        }
    }

    public void stopUse() {
        GL20.glUseProgram(0);
        isUse = false;
    }

    public void uniformMatrix4f(String name, Matrix4f mat) {
        if (isUse) {
            int loc = GL20.glGetUniformLocation(shaderProgram, name);
            FloatBuffer buffer = FloatBuffer.allocate(16);
            mat.write(buffer);
            GL20.glUniformMatrix4fv(loc, false, buffer);
        }
    }

    public void uniformFloats(String name, float... values) {
        if (isUse) {
            int loc = GL20.glGetUniformLocation(shaderProgram, name);
            switch (values.length) {
                case 1:
                    GL20.glUniform1f(loc, values[0]);
                    break;
                case 2:
                    GL20.glUniform2f(loc, values[0], values[1]);
                    break;
                case 3:
                    GL20.glUniform3f(loc, values[0], values[1], values[2]);
                    break;
                case 4:
                    GL20.glUniform4f(loc, values[0], values[1], values[2], values[3]);
                    break;
                default:
                    throw new UnsupportedOperationException("Illegal floats");

            }
        }

    }

    public void uniformBool(String name, boolean value) {
        if (isUse) {
            int loc = GL20.glGetUniformLocation(shaderProgram, name);
            GL20.glUniform1i(loc, value ? 1 : 0);
        }
    }

    public void uniformIntegers(String name, int... values) {
        if (isUse) {
            int loc = GL20.glGetUniformLocation(shaderProgram, name);
            switch (values.length) {
                case 1:
                    GL20.glUniform1i(loc, values[0]);
                    break;
                case 2:
                    GL20.glUniform2i(loc, values[0], values[1]);
                    break;
                case 3:
                    GL20.glUniform3i(loc, values[0], values[1], values[2]);
                    break;
                case 4:
                    GL20.glUniform4i(loc, values[0], values[1], values[2], values[3]);
                    break;
                default:
                    throw new UnsupportedOperationException("Illegal ints");

            }
        }

    }
}

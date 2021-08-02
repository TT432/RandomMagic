package com.nmmoc7.randommagic.tileentity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.nmmoc7.randommagic.tileentity.MagicTableTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author DustW
 */
public class MagicTableTileEntityRenderer extends TileEntityRenderer<MagicTableTileEntity> {
    private static final float DEPTH_OFFSET_PER_NONBLOCK_MODEL = 0.09375F;
    private static final float RESCALE_SIZE_ITEM = 0.575F;
    private static final float RESCALE_SIZE_BLOCK = 0.2875F;

    private final ItemRenderer itemRenderer;
    private final Random random;
    private final float hoverStart;

    public MagicTableTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
        this.random = new Random();
        this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
    }

    @Override
    public void render(MagicTableTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemStack itemstack = tileEntityIn.getStackInSlot(0);

        World world = tileEntityIn.getWorld();

        if (itemstack.isEmpty() || world == null) {
            return;
        }

        float theta = (world.getGameTime() + partialTicks) / 10F + this.hoverStart;

        matrixStackIn.push();

        this.random.setSeed(Item.getIdFromItem(itemstack.getItem()) + itemstack.getDamage());
        IBakedModel ibakedmodel = this.itemRenderer.getItemModelWithOverrides(itemstack, world, null);

        boolean isGui3d = ibakedmodel.isGui3d();
        float bobbingHeight = MathHelper.sin(theta) * 0.1F + 0.1F;

        Vector3f modelScale = ibakedmodel.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.copy();
        matrixStackIn.translate(0.5, 1 + bobbingHeight + 0.25 * modelScale.getY(), 0.5);
        matrixStackIn.rotate(Vector3f.YP.rotation(theta));

        int modelCount = this.getModelCount(itemstack);

        if (!isGui3d) {
            matrixStackIn.translate(0, 0, -0.5 * DEPTH_OFFSET_PER_NONBLOCK_MODEL * (modelCount - 1));
        }

        float maxScale = Math.max(modelScale.getX(), Math.max(modelScale.getY(), modelScale.getZ()));
        if (!isGui3d && maxScale < RESCALE_SIZE_ITEM) {
            float scale = RESCALE_SIZE_ITEM / maxScale;
            matrixStackIn.scale(scale, scale, scale);
        } else if (isGui3d && maxScale < RESCALE_SIZE_BLOCK) {
            float scale = RESCALE_SIZE_BLOCK / maxScale;
            matrixStackIn.scale(scale, scale, scale);
        }

        for(int i = 0; i < modelCount; ++i) {
            matrixStackIn.push();
            if (i > 0) {
                if (isGui3d) {
                    float dx = (this.random.nextFloat() * 2F - 1F) * 0.15F;
                    float dy = (this.random.nextFloat() * 2F - 1F) * 0.15F;
                    float dz = (this.random.nextFloat() * 2F - 1F) * 0.15F;
                    matrixStackIn.translate(dx, dy, dz);
                } else {
                    float dx = (this.random.nextFloat() * 2F - 1F) * 0.15F * 0.5F;
                    float dy = (this.random.nextFloat() * 2F - 1F) * 0.15F * 0.5F;
                    matrixStackIn.translate(dx, dy, 0);
                }
            }
            this.itemRenderer.renderItem(itemstack, ItemCameraTransforms.TransformType.GROUND, false,
                    matrixStackIn, bufferIn, combinedLightIn, OverlayTexture.NO_OVERLAY, ibakedmodel);

            matrixStackIn.pop();

            if (!isGui3d) {
                matrixStackIn.translate(0, 0, DEPTH_OFFSET_PER_NONBLOCK_MODEL);
            }
        }

        matrixStackIn.pop();
    }

    private int getModelCount(ItemStack stack) {
        int i = 1;
        if (stack.getCount() > 48) {
            i = 5;
        } else if (stack.getCount() > 32) {
            i = 4;
        } else if (stack.getCount() > 16) {
            i = 3;
        } else if (stack.getCount() > 1) {
            i = 2;
        }

        return i;
    }
}

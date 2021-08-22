package com.nmmoc7.randommagic.magic_circle.renderer.model;// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nmmoc7.randommagic.TickCounterHandler;
import com.nmmoc7.randommagic.magic_circle.entity.BigDipperEntity;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

/**
 * @author DustW
 */
public class BigDipperEntityModel extends EntityModel<Entity> {
	private final ModelRenderer group;
	private final ModelRenderer bG;
	private final ModelRenderer bGIn;
	private final ModelRenderer sRoG;
	private final ModelRenderer bRoG;
	private final ModelRenderer stars;
	/** 瑶光 */
	private final ModelRenderer alkaid;
	/** 开阳 */
	private final ModelRenderer mizar;
	/** 玉衡 */
	private final ModelRenderer alioth;
	/** 天权 */
	private final ModelRenderer megrez;
	/** 天玑 */
	private final ModelRenderer phecda;
	/** 天璇 */
	private final ModelRenderer merak;
	/** 天枢 */
	private final ModelRenderer dubhe;

	public BigDipperEntity entity;
	public float partTick;

	public BigDipperEntityModel() {
		textureWidth = 96;
		textureHeight = 96;

		group = new ModelRenderer(this);
		group.setRotationPoint(0.0F, 16.0F, 0.0F);
		

		bG = new ModelRenderer(this);
		bG.setRotationPoint(0.0F, 0.0F, 0.0F);
		group.addChild(bG);

		bGIn = new ModelRenderer(this);
		bG.setRotationPoint(0.0F, 0.0F, 0.0F);
		bG.addChild(bGIn);
		bGIn.setTextureOffset(0, 0).addBox(-24.0F, 13.0F, -24.0F, 48.0F, -10.0F, 48.0F, 5.0F, false);
		bGIn.setTextureOffset(0, 72).addBox(-12.0F, 7.5F, -12.0F, 24.0F, 0.0F, 24.0F, 0.0F, false);


		sRoG = new ModelRenderer(this);
		sRoG.setRotationPoint(0.0F, 0.0F, 0.0F);
		bG.addChild(sRoG);
		sRoG.setTextureOffset(48, 48).addBox(6.0F, 7.5F, -27.0F, 12.0F, 0.0F, 12.0F, 0.0F, false);
		sRoG.setTextureOffset(48, 48).addBox(-25.0F, 6.5F, -6.0F, 12.0F, 0.0F, 12.0F, 0.0F, false);
		sRoG.setTextureOffset(48, 48).addBox(12.0F, 6.75F, 1.0F, 12.0F, 0.0F, 12.0F, 0.0F, false);
		sRoG.setTextureOffset(48, 48).addBox(-19.0F, 7.5F, 16.0F, 12.0F, 0.0F, 12.0F, 0.0F, false);

		bRoG = new ModelRenderer(this);
		bRoG.setRotationPoint(0.0F, 0.0F, 0.0F);
		bG.addChild(bRoG);
		bRoG.setTextureOffset(0, 72).addBox(5.0F, 7.25F, 8.0F, 24.0F, 0.0F, 24.0F, 0.0F, false);
		bRoG.setTextureOffset(0, 72).addBox(-29.0F, 7.0F, -33.0F, 24.0F, 0.0F, 24.0F, 0.0F, false);

		stars = new ModelRenderer(this);
		stars.setRotationPoint(0.0F, 0.0F, 0.0F);
		group.addChild(stars);
		

		alkaid = new ModelRenderer(this);
		alkaid.setRotationPoint(0.0F, 0.0F, 0.0F);
		stars.addChild(alkaid);
		alkaid.setTextureOffset(52, 63).addBox(-26.0711F, 5.5F, -0.1421F, 9.0F, 0.0F, 9.0F, 0.0F, false);

		mizar = new ModelRenderer(this);
		mizar.setRotationPoint(0.0F, 0.0F, 0.0F);
		stars.addChild(mizar);
		mizar.setTextureOffset(52, 64).addBox(-15.0F, 5.5F, 1.0F, 9.0F, 0.0F, 9.0F, 0.0F, false);

		alioth = new ModelRenderer(this);
		alioth.setRotationPoint(0.0F, 0.0F, 0.0F);
		stars.addChild(alioth);
		alioth.setTextureOffset(52, 64).addBox(-9.0711F, 5.5F, -1.1421F, 9.0F, 0.0F, 9.0F, 0.0F, false);

		megrez = new ModelRenderer(this);
		megrez.setRotationPoint(0.0F, 0.0F, 0.0F);
		stars.addChild(megrez);
		megrez.setTextureOffset(52, 64).addBox(-0.9448F, 5.5F, -4.3596F, 9.0F, 0.0F, 9.0F, 0.0F, false);

		phecda = new ModelRenderer(this);
		phecda.setRotationPoint(0.0F, 0.0F, 0.0F);
		stars.addChild(phecda);
		phecda.setTextureOffset(52, 64).addBox(0.9289F, 5.5F, -11.1421F, 9.0F, 0.0F, 9.0F, 0.0F, false);

		merak = new ModelRenderer(this);
		merak.setRotationPoint(0.0F, 0.0F, 0.0F);
		stars.addChild(merak);
		merak.setTextureOffset(52, 63).addBox(13.9289F, 5.5F, -10.1421F, 9.0F, 0.0F, 9.0F, 0.0F, false);

		dubhe = new ModelRenderer(this);
		dubhe.setRotationPoint(0.0F, 0.0F, 0.0F);
		stars.addChild(dubhe);
		dubhe.setTextureOffset(52, 63).addBox(15.0F, 5.5F, -1.0F, 9.0F, 0.0F, 9.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		// BlurShaderHandler.INSTANCE.init(matrixStack, group, packedLight, packedOverlay);
		// BlurShaderHandler.INSTANCE.renderB(0);
		// BlurShaderHandler.INSTANCE.renderC();
		// BlurShaderHandler.INSTANCE.renderD();
		// BlurShaderHandler.INSTANCE.renderE();

		// group.render(matrixStack, buffer, packedLight, packedOverlay);

		matrixStack.push();
		matrixStack.rotate(Vector3f.YN.rotationDegrees(-getDegrees(0.7F)));
		sRoG.render(matrixStack, buffer, packedLight, packedOverlay);
		matrixStack.pop();

		matrixStack.push();
		matrixStack.rotate(Vector3f.YN.rotationDegrees(-getDegrees(0.5F)));
		bRoG.render(matrixStack, buffer, packedLight, packedOverlay);
		matrixStack.pop();

		bGIn.render(matrixStack, buffer, packedLight, packedOverlay);

		stars.render(matrixStack, buffer, getLight(), packedOverlay);
	}

	public int getLight() {
		double light = MathHelper.sin((float) (2 * Math.PI / 200 * (TickCounterHandler.tick + partTick))) / 2 + 0.5;
		int value = Math.max(7, MathHelper.fastFloor(15 * light));
		return LightTexture.packLight(value, value);
	}

	public float getDegrees(float degrees) {
		return (((TickCounterHandler.tick + partTick) * degrees)) % 360;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
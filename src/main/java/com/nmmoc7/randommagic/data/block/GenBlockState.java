package com.nmmoc7.randommagic.data.block;

import com.google.gson.JsonObject;
import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author DustW
 */
public class GenBlockState extends BlockStateProvider {
    public GenBlockState(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RandomMagic.MOD_ID, exFileHelper);
    }

    protected void objBlock(Block block) {

    }

    protected void simpleBlock(Block block, String texture) {
        simpleBlock(block, models().cubeAll("block/" + texture, modLoc("block/" + texture)));
    }

    protected void crossBlock(Block block, String texture) {
        simpleBlock(block, models().cross("block/" + texture, modLoc("block/" + texture)));
    }

    protected void logBlock(Block block, String side, String end) {
        if (!(block instanceof RotatedPillarBlock)) {
            throw new RuntimeException("Block is not rotatable.");
        }

        axisBlock((RotatedPillarBlock) block, "block/" + side, "block/" + end);
    }

    protected void axisBlock(RotatedPillarBlock block, String side, String end) {
        ModelBuilder<BlockModelBuilder> modelBuilder =
                models().cubeColumn(block.getRegistryName().getPath(), modLoc(side), modLoc(end));
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(modelBuilder).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(modelBuilder).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(modelBuilder).rotationX(90).addModel();

    }

    protected void barkBlock(Block block, String all) {
        simpleBlock(block, models().cubeAll(block.getRegistryName().getPath(), modLoc("block/" + all)));
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.MAGIC_TABLE);
    }

    private void addObjBlocks() {
        addObjBlock(ModBlocks.MAGIC_TABLE);
    }

    private void addObjBlock(Block block) {
        JsonObject jsonObject = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject value = new JsonObject();
        value.addProperty("model", RandomMagic.MOD_ID + ":block/" + block.getRegistryName().getPath());
        variants.add("", value);
        jsonObject.add("variants", variants);

        registeredBlocks.put(block, () -> jsonObject);
    }

    private void addCrops() {
    }

    private void addTrees() {
    }

    private void addBuildingBlocks() {
    }

    private void addMachineBlocks() {
    }
}


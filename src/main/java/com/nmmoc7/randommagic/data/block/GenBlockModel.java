package com.nmmoc7.randommagic.data.block;

import com.google.gson.JsonObject;
import com.nmmoc7.randommagic.RandomMagic;

import com.nmmoc7.randommagic.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

/**
 * @author DustW
 */
public class GenBlockModel extends BlockModelProvider {
    public GenBlockModel(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RandomMagic.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        addObjBlocks();
    }

    private void addObjBlocks() {
        addObjBlock(ModBlocks.MAGIC_TABLE);
    }

    private void addObjBlock(Block block) {
        String path = block.getRegistryName().getPath();
        ResourceLocation out = new ResourceLocation(RandomMagic.MOD_ID + ":block/" + path);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("loader", "forge:obj");
        jsonObject.addProperty("model", RandomMagic.MOD_ID + ":models/block/" + path + ".obj");
        jsonObject.addProperty("flip-v", true);

        generatedModels.put(block.getRegistryName(),
                new BlockModelBuilder(out, existingFileHelper) {
                    @Override
                    public JsonObject toJson() {
                        return jsonObject;
                    }
                });
    }
}

package com.nmmoc7.randommagic.block.base;

import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.block.ModBlocks;
import com.nmmoc7.randommagic.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

/**
 * @author DustW
 */
public class ModBlockBase extends Block {
    public final BlockItem ITEM;

    public ModBlockBase(String regName, AbstractBlock.Properties properties, ItemGroup group) {
        super(properties);
        setRegistryName(new ResourceLocation(RandomMagic.MOD_ID, regName));

        ITEM = new BlockItem(this, new Item.Properties().group(group));
        ITEM.setRegistryName(new ResourceLocation(RandomMagic.MOD_ID, regName));

        ModBlocks.BLOCKS.add(this);
        ModItems.BLOCK_ITEMS.add(ITEM);
    }
}

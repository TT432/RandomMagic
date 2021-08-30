package com.nmmoc7.randommagic.item;

import com.nmmoc7.randommagic.RandomMagic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class BaseItem extends Item {
    public BaseItem(String name, ItemGroup itemGroup) {
        super(new Properties().group(itemGroup));

        setRegistryName(new ResourceLocation(RandomMagic.MOD_ID, name));
    }

    public BaseItem(String name, int maxStack) {
        super(new Properties().maxStackSize(maxStack));

        setRegistryName(new ResourceLocation(RandomMagic.MOD_ID, name));
    }
}

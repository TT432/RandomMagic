package com.nmmoc7.randommagic.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/**
 * @author DustW
 */
public class MainItemGroup extends ItemGroup {
    public static final MainItemGroup GROUP = new MainItemGroup();

    public MainItemGroup() {
        super("random_magic_group");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.AIR);
    }
}

package com.nmmoc7.randommagic.block;

import com.nmmoc7.randommagic.block.base.ModBlockBase;
import com.nmmoc7.randommagic.block.base.ModObjBlock;
import com.nmmoc7.randommagic.itemgroup.MainItemGroup;
import net.minecraft.block.Block;

import java.util.ArrayList;

/**
 * @author DustW
 */
public class ModBlocks {
    public static final ArrayList<Block> BLOCKS = new ArrayList<>();
    public static final ArrayList<ModObjBlock> OBJ_BLOCKS = new ArrayList<>();

    public static final Block MAGIC_TABLE = new MagicTable();
}

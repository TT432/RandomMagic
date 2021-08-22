package com.nmmoc7.randommagic.tileentity;

import com.nmmoc7.randommagic.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;

/**
 * @author DustW
 */
public class ModTileEntityTypes {
    public final static TileEntityType<MagicTableTileEntity> MAGIC_TABLE = TileEntityType.Builder.create(MagicTableTileEntity::new, ModBlocks.MAGIC_TABLE).build(null);
}

package com.nmmoc7.randommagic.block;

import com.nmmoc7.randommagic.block.base.ModObjBlock;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistryHandler {
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        event.getRegistry().registerAll(ModBlocks.OBJ_BLOCKS.toArray(new ModObjBlock[0]));
    }
}

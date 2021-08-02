package com.nmmoc7.randommagic.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegistryHandler {
    @SubscribeEvent
    public static void onTileEntityRegister(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                ModTileEntityTypes.MAGIC_TABLE.setRegistryName("magic_table_tile_entity")
        );
    }
}

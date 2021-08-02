package com.nmmoc7.randommagic.tileentity;

import com.nmmoc7.randommagic.tileentity.render.MagicTableTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRendererRegistryHandler {
    @SubscribeEvent
    public static void onTileEntityRendererRegister(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.MAGIC_TABLE, MagicTableTileEntityRenderer::new);
        });
    }
}

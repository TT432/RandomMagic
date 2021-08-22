package com.nmmoc7.randommagic.magic_circle.renderer;

import com.nmmoc7.randommagic.magic_circle.entity.EntityTypeRegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRendererBindHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
        rendererManager.register(EntityTypeRegistryHandler.BIG_DIPPER, new BigDipperEntityRenderer(rendererManager));
        rendererManager.register(EntityTypeRegistryHandler.TEST_GIF, new TestGIFEntityRenderer(rendererManager));
    }
}

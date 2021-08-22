package com.nmmoc7.randommagic.magic_circle.gif;

import com.nmmoc7.randommagic.RandomMagic;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GIFS {
    public static final ResourceLocation TEST_GIF = new ResourceLocation(RandomMagic.MOD_ID, "gif/test_gif.gif");

    public static GIFInstance TEST;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TEST = new GIFInstance(TEST_GIF);
        });
    }
}

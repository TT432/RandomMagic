package com.nmmoc7.randommagic.magic_circle.renderer;

import com.nmmoc7.phoenixlib.client.gif.GIFInstance;
import com.nmmoc7.phoenixlib.event.client.GIFRegisterEvent;
import com.nmmoc7.randommagic.RandomMagic;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class GIFRegisterHandler {
    public static final ResourceLocation TEST_GIF = new ResourceLocation(RandomMagic.MOD_ID, "gif/test_gif.gif");

    public static Lazy<GIFInstance> TEST = Lazy.of(() -> new GIFInstance(TEST_GIF));

    @SubscribeEvent
    public static void onClientSetup(GIFRegisterEvent event) {
        event.register(TEST.get());
    }
}

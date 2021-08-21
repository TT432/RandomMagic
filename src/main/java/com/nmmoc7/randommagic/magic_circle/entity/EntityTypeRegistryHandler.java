package com.nmmoc7.randommagic.magic_circle.entity;

import com.nmmoc7.randommagic.RandomMagic;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTypeRegistryHandler {
    public static final ResourceLocation BIG_DIPPER_NAME = new ResourceLocation(RandomMagic.MOD_ID, "big_dipper");
    public static final EntityType<BigDipperEntity> BIG_DIPPER = EntityType.Builder
            .create(BigDipperEntity::new, EntityClassification.MISC).build(BIG_DIPPER_NAME.toString());

    @SubscribeEvent
    public static void register(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                BIG_DIPPER.setRegistryName(BIG_DIPPER_NAME)
        );
    }
}

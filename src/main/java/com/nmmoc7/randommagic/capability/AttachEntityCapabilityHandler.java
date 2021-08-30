package com.nmmoc7.randommagic.capability;

import com.nmmoc7.randommagic.RandomMagic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class AttachEntityCapabilityHandler {
    @SubscribeEvent
    public static void onAttachEntityCapabilityEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(RandomMagic.MOD_ID, "magic"), new CapabilityManaProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            PlayerEntity original = event.getOriginal();
            PlayerEntity target = event.getPlayer();

            CapabilityMana originalCap = CapabilityMana.getCapability(original);
            CapabilityMana targetCap = CapabilityMana.getCapability(target);
            targetCap.deserializeNBT(originalCap.serializeNBT());
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();

        if (!player.world.isRemote) {
            CapabilityMana.getCapability(player).onChanged(player);
        }
    }
}


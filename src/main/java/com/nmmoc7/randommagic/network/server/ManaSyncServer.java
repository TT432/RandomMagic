package com.nmmoc7.randommagic.network.server;

import com.nmmoc7.randommagic.capability.CapabilityMana;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaSyncServer implements IServerMessage {
    int maxMana;
    int mana;

    public ManaSyncServer(int maxMana, int mana) {
        this.maxMana = maxMana;
        this.mana = mana;
    }

    public static void encode(ManaSyncServer msg, PacketBuffer packetBuffer) {
        packetBuffer.writeInt(msg.maxMana);
        packetBuffer.writeInt(msg.mana);
    }

    public static ManaSyncServer decode(PacketBuffer packetBuffer) {
        return new ManaSyncServer(packetBuffer.readInt(), packetBuffer.readInt());
    }

    public static void handle(ManaSyncServer msg, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            PlayerEntity player = IServerMessage.getPlayer();
            CapabilityMana capa = CapabilityMana.getCapability(player);
            capa.setMaxMana(player, msg.maxMana);
            capa.setMana(player, msg.mana);
        });
    }
}

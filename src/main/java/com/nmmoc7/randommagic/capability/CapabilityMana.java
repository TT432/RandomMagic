package com.nmmoc7.randommagic.capability;

import com.nmmoc7.randommagic.network.ModNetworkManager;
import com.nmmoc7.randommagic.network.server.ManaSyncServer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.Optional;

public class CapabilityMana implements INBTSerializable<CompoundNBT> {
    @CapabilityInject(CapabilityMana.class)
    public static Capability<CapabilityMana> CAPABILITY;
    @Nullable
    public static CapabilityMana getCapability(PlayerEntity player) {
        Optional<CapabilityMana> cap = player.getCapability(CAPABILITY).resolve();
        return cap.orElse(null);
    }

    private int maxMana;
    private int mana;

    public void setMana(PlayerEntity player, int mana) {
        if (mana != this.mana) {
            this.mana = mana;
            onChanged(player);
        }
    }

    public void setMaxMana(PlayerEntity player, int maxMana) {
        if (maxMana != this.maxMana) {
            this.maxMana = maxMana;
            onChanged(player);
        }
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void onChanged(PlayerEntity player) {
        if (!player.world.isRemote) {
            ModNetworkManager.serverSendToPlayer(new ManaSyncServer(maxMana, mana), (ServerPlayerEntity) player);
        }
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }
}

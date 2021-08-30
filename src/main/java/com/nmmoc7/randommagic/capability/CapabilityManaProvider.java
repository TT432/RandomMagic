package com.nmmoc7.randommagic.capability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityManaProvider implements ICapabilityProvider {
    private static final LazyOptional<CapabilityMana> CAPABILITY = LazyOptional.of(CapabilityMana::new);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CapabilityMana.CAPABILITY ? CAPABILITY.cast() : LazyOptional.empty();
    }
}

package com.nmmoc7.randommagic.util;

import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class VarLazy<T> implements Lazy<T> {
    private Supplier<T> supplier;
    private T instance;

    public VarLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public final void set(T instance) {
        this.instance = instance;
        supplier = () -> instance;
    }

    @Nullable
    @Override
    public final T get() {
        if (supplier != null) {
            instance = supplier.get();
            supplier = null;
        }
        return instance;
    }
}

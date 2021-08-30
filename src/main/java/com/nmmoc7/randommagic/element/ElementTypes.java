package com.nmmoc7.randommagic.element;

import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.api.element.ElementType;
import com.nmmoc7.randommagic.api.registry.RandomMagicRegistrties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class ElementTypes {
    public static final DeferredRegister<ElementType> ELEMENT_TYPES = DeferredRegister.create(RandomMagicRegistrties.ELEMENT_TYPES, RandomMagic.MOD_ID);

    public static final RegistryObject<ElementType> WIND = register("wind");
    public static final RegistryObject<ElementType> WATER = register("water");
    public static final RegistryObject<ElementType> EARTH = register("earth");
    public static final RegistryObject<ElementType> FIRE = register("fire");

    private static RegistryObject<ElementType> register(String name) {
        return ELEMENT_TYPES.register("type_" + name, ElementType::new);
    }
}

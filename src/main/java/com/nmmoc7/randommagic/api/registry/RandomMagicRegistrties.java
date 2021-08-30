package com.nmmoc7.randommagic.api.registry;

import com.nmmoc7.randommagic.api.element.Element;
import com.nmmoc7.randommagic.api.element.ElementType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class RandomMagicRegistrties {
    public static final IForgeRegistry<ElementType> ELEMENT_TYPES = RegistryManager.ACTIVE.getRegistry(ElementType.class);

    public static final IForgeRegistry<Element> ELEMENTS = RegistryManager.ACTIVE.getRegistry(Element.class);
}

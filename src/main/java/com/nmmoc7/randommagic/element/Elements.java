package com.nmmoc7.randommagic.element;

import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.api.element.Element;
import com.nmmoc7.randommagic.api.element.ElementType;
import com.nmmoc7.randommagic.api.registry.RandomMagicRegistrties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class Elements {
    public static final DeferredRegister<Element> ELEMENTS = DeferredRegister.create(RandomMagicRegistrties.ELEMENTS, RandomMagic.MOD_ID);

    /**
     * 风
     * 风 + 水 = 蒸汽
     * 风 + 土 = 尘土
     * 火 + 尘土 = 灰烬
     * 能量 + 风 = 风暴
     */
    public static final RegistryObject<Element> WIND = register("wind", ElementTypes.WIND);
    public static final RegistryObject<Element> STEAM = register("steam", ElementTypes.WIND);
    public static final RegistryObject<Element> DUST = register("dust", ElementTypes.WIND);
    public static final RegistryObject<Element> ASH = register("ash", ElementTypes.WIND);
    public static final RegistryObject<Element> STORM = register("storm", ElementTypes.WIND);

    /**
     * 水
     * 火 + 水 =   酒精
     * 水 + 金属 = 水银
     * 酒精 + 水 = 伏特加
     * 土 + 水 =   沼泽
     * 水 + 玻璃 = 冰
     */
    public static final RegistryObject<Element> WATER = register("water", ElementTypes.WATER);
    public static final RegistryObject<Element> ALCOHOL = register("alcohol", ElementTypes.WATER);
    public static final RegistryObject<Element> MERCURY = register("mercury", ElementTypes.WATER);
    public static final RegistryObject<Element> VODKA = register("vodka", ElementTypes.WATER);
    public static final RegistryObject<Element> SWAMP = register("swamp", ElementTypes.WATER);
    public static final RegistryObject<Element> ICE = register("ice", ElementTypes.WATER);

    /**
     * 土
     * 风 + 岩浆 =   石头
     * 火 + 石头 =   金属
     * 风 + 石头 =   沙子
     * 火 + 沙子 =   玻璃
     * 沙子 + 沼泽 = 粘土
     */
    public static final RegistryObject<Element> EARTH = register("earth", ElementTypes.EARTH);
    public static final RegistryObject<Element> STONE = register("stone", ElementTypes.EARTH);
    public static final RegistryObject<Element> METAL = register("metal", ElementTypes.EARTH);
    public static final RegistryObject<Element> SAND = register("sand", ElementTypes.EARTH);
    public static final RegistryObject<Element> GRASS = register("grass", ElementTypes.EARTH);
    public static final RegistryObject<Element> CLAY = register("clay", ElementTypes.EARTH);

    /**
     * 火
     * 土 + 火 =   岩浆
     * 能量 + 火 = 等离子体
     * 火 + 树 =   煤
     * 能量 + 煤 = 硫磺
     * 煤炭 + 水 = 石油
     */
    public static final RegistryObject<Element> FIRE = register("fire", ElementTypes.FIRE);
    public static final RegistryObject<Element> MAGMA = register("magma", ElementTypes.FIRE);
    public static final RegistryObject<Element> PLASMA = register("plasma", ElementTypes.FIRE);
    public static final RegistryObject<Element> COAL = register("coal", ElementTypes.FIRE);
    public static final RegistryObject<Element> SULFUR = register("sulfur", ElementTypes.FIRE);
    public static final RegistryObject<Element> OIL = register("oil", ElementTypes.FIRE);

    private static RegistryObject<Element> register(String name, RegistryObject<ElementType> type) {
        return ELEMENTS.register(name, () -> new Element(type.get()));
    }
}

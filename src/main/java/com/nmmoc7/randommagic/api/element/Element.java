package com.nmmoc7.randommagic.api.element;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.List;

public class Element extends ForgeRegistryEntry<Element> {
    private String translationKey;
    private final ElementType type;

    public Element(ElementType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return getRegistryName().hashCode();
    }

    @Override
    public String toString() {
        return getRegistryName().getPath();
    }

    public ElementType getType() {
        return type;
    }

    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(this.getTranslationKey());
    }

    public void addInformation(List<ITextComponent> tooltip) {
    }

    protected String getTranslationKey() {
        return this.getDefaultTranslationKey();
    }

    protected String getDefaultTranslationKey() {
        if (this.translationKey == null) {
            this.translationKey = Util.makeTranslationKey("element", getRegistryName());
        }

        return this.translationKey;
    }
}

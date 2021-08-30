package com.nmmoc7.randommagic.api.element;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.List;

public class ElementType extends ForgeRegistryEntry<ElementType> {
    private String translationKey;

    @Override
    public int hashCode() {
        return getRegistryName().hashCode();
    }

    @Override
    public String toString() {
        return getRegistryName().getPath();
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

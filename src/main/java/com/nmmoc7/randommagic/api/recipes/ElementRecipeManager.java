package com.nmmoc7.randommagic.api.recipes;

import com.nmmoc7.randommagic.api.element.Element;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElementRecipeManager {
    private static final Map<Element, Map<Element, Element>> RECIPES = new HashMap<>();

    private static Map<Element, Element> getOrCreate(Element in1) {
        Map<Element, Element> temp = RECIPES.get(in1);

        if (temp == null) {
            RECIPES.put(in1, new HashMap<>());
        }

        return temp;
    }

    public static void addRecipe(Element in1, Element in2, Element out) {
        getOrCreate(in1).put(in2, out);
    }

    @Nullable
    public static Element match(Element in1, Element in2) {
        return getOrCreate(in1).get(in2);
    }
}

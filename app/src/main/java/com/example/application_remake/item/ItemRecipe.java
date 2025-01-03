package com.example.application_remake.item;

import java.util.List;

public class ItemRecipe {
    private String name;
    private String preparation;
    private List<ItemIngredient> ingredients;

    public ItemRecipe(String name, String preparation, List<ItemIngredient> ingredients) {
        this.name = name;
        this.preparation = preparation;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public List<ItemIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<ItemIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}

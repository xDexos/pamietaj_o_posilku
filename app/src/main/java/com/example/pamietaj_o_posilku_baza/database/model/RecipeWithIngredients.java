package com.example.pamietaj_o_posilku_baza.database.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;

    @Relation(
            entity = Ingredient.class,
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(
                    value = RecipeIngredientCrossRef.class,
                    parentColumn = "recipeId",
                    entityColumn = "ingredientId"
            )
    )
    public List<Ingredient> ingredients;
}

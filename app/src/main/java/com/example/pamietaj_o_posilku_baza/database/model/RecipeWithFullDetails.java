package com.example.pamietaj_o_posilku_baza.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithFullDetails {
    @Embedded
    public Recipe recipe;

    @Relation(
            entity = RecipeIngredientCrossRef.class,
            parentColumn = "id",
            entityColumn = "recipeId"
    )
    public List<RecipeIngredientWithDetails> ingredients;
}

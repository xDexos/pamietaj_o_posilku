package com.example.application_remake.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.Recipe;
import com.example.application_remake.database.table.RecipeIngredientCrossRef;

import java.util.List;

public class RecipeWithIngredients {

    @Embedded public Recipe recipeEntity;
    @Relation(
            parentColumn = "recipe_id",
            entityColumn = "ingredient_id",
            associateBy = @Junction(RecipeIngredientCrossRef.class)
    )
    public List<Ingredient> ingredientsEntities;
}

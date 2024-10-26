package com.example.pamietajoposilku;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert
    void insertRecipe(Recipe recipe);

    @Insert
    void insertIngredients(List<Ingredient> ingredients);

    @Query("SELECT * FROM Recipe")
    List<Recipe> getAllRecipes();
}

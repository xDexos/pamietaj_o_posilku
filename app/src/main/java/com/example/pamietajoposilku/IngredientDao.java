package com.example.pamietajoposilku;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {
    @Insert
    void insertIngredient(Ingredient ingredient);

    @Query("SELECT * FROM Ingredient WHERE _recipeId = :recipeId")
    List<Ingredient> getIngredientsForRecipe(int recipeId);
}

package com.example.pamietaj_o_posilku_baza.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pamietaj_o_posilku_baza.database.model.RecipeIngredientCrossRef;

import java.util.List;

@Dao
public interface RecipeIngredientCrossRefDao {
    @Insert
    void insert(RecipeIngredientCrossRef crossRef);

    @Query("SELECT * FROM recipe_ingredient_cross_ref WHERE recipeId = :recipeId")
    List<RecipeIngredientCrossRef> getIngredientsForRecipe(int recipeId);
}

package com.example.pamietaj_o_posilku_baza.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.pamietaj_o_posilku_baza.database.model.Recipe;
import com.example.pamietaj_o_posilku_baza.database.model.RecipeWithFullDetails;
import com.example.pamietaj_o_posilku_baza.database.model.RecipeWithIngredients;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert
    long insert(Recipe recipe);

    @Query("SELECT * FROM recipes")
    List<Recipe> getAllRecipes();

    @Transaction
    @Query("SELECT * FROM recipes")
    List<RecipeWithIngredients> getRecipesWithIngredients();

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    RecipeWithFullDetails getRecipeWithFullDetails(int recipeId);

    @Transaction
    @Query("SELECT * FROM recipes")
    List<RecipeWithFullDetails> getAllRecipesWithFullDetails();

    @Delete
    void delete(Recipe recipe);
}
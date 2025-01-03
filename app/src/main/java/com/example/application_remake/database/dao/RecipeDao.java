package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addRecipe(Recipe recipe);

    @Update
    public void updateRecipe(Recipe recipe);

    @Delete
    public void deleteRecipe(Recipe recipe);

    @Query("SELECT * FROM recipes")
    public LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE recipe_id == :recipe_id")
    public LiveData<Recipe> getRecipe(long recipe_id);

    @Query("UPDATE recipes SET recipe_note = :note WHERE recipe_id = :recipe_id")
    public void setRecipeNote(long recipe_id, String note);

//    @Transaction
//    @Query("SELECT * FROM recipes")
//    public LiveData<List<RecipeWithIngredients>> getRecipeWithIngredients();

    // ======================================================

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public Comparable insertRecipes(List<Recipe> recipes);
//
//    @Update
//    public Comparable updateRecipes(List<Recipe> recipes);
//
//    @Delete
//    public Comparable deleteRecipes(List<Recipe> recipes);
//
//    @Query("SELECT * FROM recipes WHERE recipe_id = :recipe_id")
//    public Single<Recipe> loadRecipeById(long recipe_id);
//
//    @Query("SELECT * FROM recipes")
//    public Flowable<List<Recipe>> loadRecipes();
//
//    @Transaction
//    @Query("SELECT * FROM recipes")
//    public Flowable<List<RecipeWithIngredients>> loadRecipeWithIngredients(List<Ingredient> ingredients);
}

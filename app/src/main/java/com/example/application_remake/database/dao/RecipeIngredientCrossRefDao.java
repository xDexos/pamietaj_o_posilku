package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.application_remake.database.IngredientWithRecipes;
import com.example.application_remake.database.RecipeWithIngredients;
import com.example.application_remake.database.table.RecipeIngredientCrossRef;

import java.util.List;

@Dao
public interface RecipeIngredientCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addRecipeIngredientCrossRefs(List<RecipeIngredientCrossRef> recipeIngredientCrossRef);

    @Update
    public void updateRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef);

    @Delete
    public void deleteRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef);



    @Transaction
    @Query("SELECT * FROM recipes")
    public LiveData<List<RecipeWithIngredients>> getRecipeWithIngredients();

    @Transaction
    @Query("SELECT * FROM ingredients")
    public LiveData<List<IngredientWithRecipes>> getIngredientWithRecipes();

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipe_id == :recipe_id")
    public LiveData<RecipeWithIngredients> getRecipeWithIngredients(long recipe_id);

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipe_id == :recipe_id")
    public RecipeWithIngredients getRecipeWithIngredients2(long recipe_id);

    @Transaction
    @Query("SELECT * FROM ingredients WHERE ingredient_id == :ingredient_id")
    public LiveData<IngredientWithRecipes> getIngredientWithRecipes(long ingredient_id);
}

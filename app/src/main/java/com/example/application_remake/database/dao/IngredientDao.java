package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.IngredientWithRecipes;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addIngredient(Ingredient ingredient);

    @Update
    public void updateIngredient(Ingredient ingredient);

    @Delete
    public void deleteIngredient(Ingredient ingredient);

    @Query("SELECT * FROM ingredients")
    public LiveData<List<Ingredient>> getAllIngredients();

    @Query("SELECT * FROM ingredients WHERE ingredient_id == :ingredient_id")
    public LiveData<Ingredient> getIngredient(int ingredient_id);

    @Query("SELECT * FROM ingredients WHERE ingredient_type == :ingredient_type")
    public LiveData<List<Ingredient>> getIngredientsByType(String ingredient_type);

//    @Transaction
//    @Query("SELECT * FROM ingredients")
//    public LiveData<List<IngredientWithRecipes>> getIngredientWithRecipes();
}
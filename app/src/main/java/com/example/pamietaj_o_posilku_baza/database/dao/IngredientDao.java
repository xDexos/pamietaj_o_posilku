package com.example.pamietaj_o_posilku_baza.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pamietaj_o_posilku_baza.database.model.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {
    @Insert
    long insert(Ingredient ingredient);

    @Query("SELECT * FROM ingredients")
    List<Ingredient> getAllIngredients();

    @Query("SELECT * FROM ingredients WHERE id = :id")
    Ingredient getIngredientById(int id);
}
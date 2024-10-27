package com.example.pamietaj_o_posilku_baza;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pamietaj_o_posilku_baza.database.dao.IngredientDao;
import com.example.pamietaj_o_posilku_baza.database.dao.RecipeDao;
import com.example.pamietaj_o_posilku_baza.database.dao.RecipeIngredientCrossRefDao;
import com.example.pamietaj_o_posilku_baza.database.dao.UnitDao;
import com.example.pamietaj_o_posilku_baza.database.model.Ingredient;
import com.example.pamietaj_o_posilku_baza.database.model.Recipe;
import com.example.pamietaj_o_posilku_baza.database.model.RecipeIngredientCrossRef;
import com.example.pamietaj_o_posilku_baza.database.model.Unit;

@Database(entities = {Recipe.class, Ingredient.class, Unit.class, RecipeIngredientCrossRef.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract UnitDao unitDao();
    public abstract RecipeIngredientCrossRefDao recipeIngredientCrossRefDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "recipe_database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
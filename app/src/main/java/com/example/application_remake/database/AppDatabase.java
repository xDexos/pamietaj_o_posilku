package com.example.application_remake.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.application_remake.database.dao.BloodPressureDao;
import com.example.application_remake.database.dao.BloodSugarDao;
import com.example.application_remake.database.dao.DailyActivityDao;
import com.example.application_remake.database.dao.DailyMoodDao;
import com.example.application_remake.database.dao.IngredientDao;
import com.example.application_remake.database.dao.PlannedMealDao;
import com.example.application_remake.database.dao.RecipeDao;
import com.example.application_remake.database.dao.RecipeIngredientCrossRefDao;
import com.example.application_remake.database.dao.ShoppingListDao;
import com.example.application_remake.database.dao.ShoppingListIngredientCrossRefDao;
import com.example.application_remake.database.dao.WeightDao;
import com.example.application_remake.database.table.BloodPressure;
import com.example.application_remake.database.table.BloodSugar;
import com.example.application_remake.database.table.DailyActivity;
import com.example.application_remake.database.table.DailyMood;
import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.PlannedMeal;
import com.example.application_remake.database.table.Recipe;
import com.example.application_remake.database.table.RecipeIngredientCrossRef;
import com.example.application_remake.database.table.ShoppingList;
import com.example.application_remake.database.table.ShoppingListIngredientCrossRef;
import com.example.application_remake.database.table.Weight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class, Ingredient.class, RecipeIngredientCrossRef.class, PlannedMeal.class, ShoppingList.class, ShoppingListIngredientCrossRef.class, Weight.class, BloodPressure.class, DailyActivity.class, BloodSugar.class, DailyMood.class},
        version = 23)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public abstract RecipeDao getRecipeDao();
    public abstract IngredientDao getIngredientDao();
    public abstract RecipeIngredientCrossRefDao getRecipeIngredientCrossRefDao();
    public abstract PlannedMealDao getPlannedMealDao();
    public abstract ShoppingListDao getShoppingListDao();
    public abstract ShoppingListIngredientCrossRefDao getShoppingListIngredientCrossRefDao();
    public abstract WeightDao getWeightDao();
    public abstract BloodPressureDao getBloodPressureDao();
    public abstract DailyActivityDao getDailyActivityDao();
    public abstract BloodSugarDao getBloodSugarDao();
    public abstract DailyMoodDao getDailyMoodDao();

    // Dodaj databaseWriteExecutor
    public static final ExecutorService executorWriteService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    )
                    .fallbackToDestructiveMigration() // Ta opcja usuwa i odbudowuje bazę w razie zmiany wersji
                    .build();
        }
        return INSTANCE;
    }

}

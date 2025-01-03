package com.example.application_remake.database.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.application_remake.database.AppDatabase;
import com.example.application_remake.database.IngredientsWithShoppingLists;
import com.example.application_remake.database.RecipeWithIngredients;
import com.example.application_remake.database.ShoppingListWithIngredients;
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

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RecipeRepository {

    public final AppDatabase db;
    private final RecipeDao recipeDao;
    private final IngredientDao ingredientDao;
    private final RecipeIngredientCrossRefDao recipeIngredientCrossRefDao;
    private final PlannedMealDao plannedMealDao;
    private final ShoppingListDao shoppingListDao;
    private final ShoppingListIngredientCrossRefDao shoppingListIngredientCrossRefDao;
    private final WeightDao weightDao;
    private final BloodPressureDao bloodPressureDao;
    private final DailyActivityDao dailyActivityDao;
    private final BloodSugarDao bloodSugarDao;
    private final DailyMoodDao dailyMoodDao;


    private final ExecutorService executorService;

    public RecipeRepository(Context context) {
        db = AppDatabase.getInstance(context);
        recipeDao = db.getRecipeDao();
        ingredientDao = db.getIngredientDao();
        recipeIngredientCrossRefDao = db.getRecipeIngredientCrossRefDao();
        plannedMealDao = db.getPlannedMealDao();
        shoppingListDao = db.getShoppingListDao();
        shoppingListIngredientCrossRefDao = db.getShoppingListIngredientCrossRefDao();
        executorService = Executors.newSingleThreadExecutor();
        weightDao = db.getWeightDao();
        bloodPressureDao = db.getBloodPressureDao();
        dailyActivityDao = db.getDailyActivityDao();
        bloodSugarDao = db.getBloodSugarDao();
        dailyMoodDao = db.getDailyMoodDao();
    }

    public void insertRecipe(Recipe recipe) {
        executorService.execute(() -> {
            recipeDao.addRecipe(recipe);
        });
    }

    public void updateRecipe(Recipe recipe) {
        executorService.execute(() -> {
            recipeDao.updateRecipe(recipe);
        });
    }

    public void deleteRecipe(Recipe recipe) {
        executorService.execute(() -> {
            recipeDao.deleteRecipe(recipe);
        });
    }

    public void insertIngredient(Ingredient ingredient) {
        executorService.execute(() -> {
            ingredientDao.addIngredient(ingredient);
        });
    }

    public void updateIngredient(Ingredient ingredient) {
        executorService.execute(() -> {
            ingredientDao.updateIngredient(ingredient);
        });
    }

    public void deleteIngredient(Ingredient ingredient) {
        executorService.execute(() -> {
            ingredientDao.deleteIngredient(ingredient);
        });
    }

    public void setRecipeNote(long recipeId, String note) {
        executorService.execute(() -> {
            recipeDao.setRecipeNote(recipeId, note);
        });
    }

    public LiveData<Recipe> getRecipeById(long recipeId) {
        return recipeDao.getRecipe(recipeId);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return ingredientDao.getAllIngredients();
    }

    public LiveData<List<Ingredient>> getIngredientsByType(String ingredient_type) {
        return ingredientDao.getIngredientsByType(ingredient_type);
    }

    public void insertRecipeWithIngredients(Recipe recipe, List<Ingredient> ingredients) {
        executorService.execute(() -> {
            try {
                long recipeId = recipeDao.addRecipe(recipe);
                Log.d("Database", "Inserted recipe with ID: " + recipeId);
                recipe.setId(recipeId);

                for (Ingredient ingredient : ingredients) {
                    long ingredientId = ingredientDao.addIngredient(ingredient);
                    Log.d("Database", "Inserted ingredient with ID: " + ingredientId);
                    ingredient.setId(ingredientId);

                    RecipeIngredientCrossRef crossRef = new RecipeIngredientCrossRef(recipeId, ingredientId);
                    recipeIngredientCrossRefDao.addRecipeIngredientCrossRef(crossRef);
                    Log.d("Database", "Created cross reference: recipe_id=" + recipeId + ", ingredient_id=" + ingredientId);
                }
            } catch (Exception e) {
                Log.e("Database", "Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void insertRecipeWithIngredients2(Recipe recipe, List<Ingredient> ingredients) {
        executorService.execute(() -> {
            try {
                // Dodaj nowy przepis
                long recipeId = recipeDao.addRecipe(recipe);
                Log.d("Database", "Inserted recipe with ID: " + recipeId);
                recipe.setId(recipeId);

                // Dla każdego wybranego składnika utwórz tylko referencję
                for (Ingredient ingredient : ingredients) {
                    // Używamy istniejącego ID składnika
                    RecipeIngredientCrossRef crossRef = new RecipeIngredientCrossRef(recipeId, ingredient.getId());
                    recipeIngredientCrossRefDao.addRecipeIngredientCrossRef(crossRef);
                    Log.d("Database", "Created cross reference: recipe_id=" + recipeId + ", ingredient_id=" + ingredient.getId());
                }
            } catch (Exception e) {
                Log.e("Database", "Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void insertPlannedMeal(PlannedMeal plannedMeal) {
        executorService.execute(() -> {
            plannedMealDao.addPlannedMeal(plannedMeal);
        });
    }

    public void deletePlannedMealById(long plannedMealId) {
        executorService.execute(() -> {
            plannedMealDao.deletePlannedMealById(plannedMealId);
        });
    }

    public LiveData<List<PlannedMeal>> getAllPlannedMeals() {
        return plannedMealDao.getAllPlannedMeals();
    }

    public LiveData<List<PlannedMeal>> getPlannedMealsByDate(String meal_date) {
        return plannedMealDao.getPlannedMealsByDate(meal_date);
    }

    public List<PlannedMeal> getPlannedMealsByDate2(String meal_date) {
        return plannedMealDao.getPlannedMealsByDate2(meal_date);
    }


    public void addRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef) {
        executorService.execute(() -> {
            recipeIngredientCrossRefDao.addRecipeIngredientCrossRef(recipeIngredientCrossRef);
        });
    }

    public void addRecipeIngredientCrossRefs(List<RecipeIngredientCrossRef> recipeIngredientCrossRefs) {
        executorService.execute(() -> {
            recipeIngredientCrossRefDao.addRecipeIngredientCrossRefs(recipeIngredientCrossRefs);
        });
    }

    public void updateRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef) {
        executorService.execute(() -> {
            recipeIngredientCrossRefDao.updateRecipeIngredientCrossRef(recipeIngredientCrossRef);
        });
    }

    public void deleteRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef) {
        executorService.execute(() -> {
            recipeIngredientCrossRefDao.deleteRecipeIngredientCrossRef(recipeIngredientCrossRef);
        });
    }

    public LiveData<RecipeWithIngredients> getRecipeWithIngredients(long recipeId) {
        return recipeIngredientCrossRefDao.getRecipeWithIngredients(recipeId);
    }

    public RecipeWithIngredients getRecipeWithIngredients2(long recipeId) {
        return recipeIngredientCrossRefDao.getRecipeWithIngredients2(recipeId);
    }

    // ShoppingList

//    public long addShoppingList(ShoppingList shoppingList) {
//        executorService.execute(() -> {
//            shoppingListDao.insert(shoppingList);
//        });
//        return shoppingList.getId();
//    }

    public long addShoppingList(ShoppingList shoppingList) {
        Future<Long> future = executorService.submit(() -> shoppingListDao.insert(shoppingList));
        try {
            return future.get(); // Blokuje do zakończenia operacji w tle
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return -1; // Wartość oznaczająca błąd
        }
    }


    public void updateShoppingList(ShoppingList shoppingList) {
        executorService.execute(() -> {
            shoppingListDao.update(shoppingList);
        });
    }

    public void deleteShoppingList(ShoppingList shoppingList) {
        executorService.execute(() -> {
            shoppingListDao.delete(shoppingList);
        });
    }

    public LiveData<List<ShoppingList>> getAllShoppingLists() {
        return shoppingListDao.getAllShoppingLists();
    }

    public LiveData<ShoppingList> getShoppingListById(long shoppingListId) {
        return shoppingListDao.getShoppingListById(shoppingListId);
    }

    // ShoppingListCrossRef

    public LiveData<List<Ingredient>> getIngredientsForShoppingList(long shoppingListId, String ingredientType) {
        return shoppingListIngredientCrossRefDao.getIngredientsForShoppingList(shoppingListId, ingredientType);
    }

    public void addIngredientToShoppingList(long shoppingListId, long ingredientId) {
        ShoppingListIngredientCrossRef sli = new ShoppingListIngredientCrossRef() {
        };
        sli.shopping_list_id = shoppingListId;
        sli.ingredient_id = ingredientId;
        AppDatabase.executorWriteService.execute(() -> shoppingListIngredientCrossRefDao.addShoppingListIngredientCrossRef(sli));
    }

    public void addShoppingListIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef) {
        executorService.execute(() -> {
            shoppingListIngredientCrossRefDao.addShoppingListIngredientCrossRef(shoppingListIngredientCrossRef);
        });
    }

    public void addShoppingListIngredientCrossRefs(List<ShoppingListIngredientCrossRef> shoppingListIngredientCrossRefs) {
        executorService.execute(() -> {
            shoppingListIngredientCrossRefDao.addShoppingListIngredientCrossRefs(shoppingListIngredientCrossRefs);
        });
    }

    public void updateShoppingListIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef) {
        executorService.execute(() -> {
            shoppingListIngredientCrossRefDao.updateShoppingListIngredientCrossRef(shoppingListIngredientCrossRef);
        });
    }

    public void deleteShoppingListIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef) {
        executorService.execute(() -> {
            shoppingListIngredientCrossRefDao.deleteShoppingListIngredientCrossRef(shoppingListIngredientCrossRef);
        });
    }

    public void removeIngredientFromShoppingList(long shoppingListId, long ingredientId) {
        AppDatabase.executorWriteService.execute(() -> {
            shoppingListIngredientCrossRefDao.deleteByIds(shoppingListId, ingredientId);
        });
    }

    public LiveData<List<ShoppingListWithIngredients>> getShoppingListWithIngredients() {
        return shoppingListIngredientCrossRefDao.getShoppingListWithIngredients();
    }

    public LiveData<List<IngredientsWithShoppingLists>> getIngredientWithShoppingLists() {
        return shoppingListIngredientCrossRefDao.getIngredientWithShoppingLists();
    }

    public LiveData<IngredientsWithShoppingLists> getIngredientWithShoppingLists(long ingredientId) {
        return shoppingListIngredientCrossRefDao.getIngredientWithShoppingLists(ingredientId);
    }

    public LiveData<ShoppingListWithIngredients> getShoppingListWithIngredients(long shoppingListId) {
        return shoppingListIngredientCrossRefDao.getShoppingListWithIngredients(shoppingListId);
    }

    // Weight

    public void insertWeight(Weight weight) {
        executorService.execute(() -> {
            weightDao.addWeight(weight);
        });
    }

    public void updateWeight(Weight weight) {
        executorService.execute(() -> {
            weightDao.updateWeight(weight);
        });
    }

    public void deleteWeight(Weight weight) {
        executorService.execute(() -> {
            weightDao.deleteWeight(weight);
        });
    }

    public LiveData<List<Weight>> getAllWeights() {
        return weightDao.getAllWeight();
    }

    //BloodPressure

    public void insertBloodPressure(BloodPressure bloodPressure) {
        executorService.execute(() -> {
            bloodPressureDao.addBloodPressure(bloodPressure);
        });
    }

    public void updateBloodPressure(BloodPressure bloodPressure) {
        executorService.execute(() -> {
            bloodPressureDao.updateBloodPressure(bloodPressure);
        });
    }

    public void deleteBloodPressure(BloodPressure bloodPressure) {
        executorService.execute(() -> {
            bloodPressureDao.deleteBloodPressure(bloodPressure);
        });
    }

    public LiveData<List<BloodPressure>> getAllBloodPressures() {
        return bloodPressureDao.getAllBloodPressure();
    }

    //DailyActivity

    public void insertDailyActivity(DailyActivity dailyActivity) {
        executorService.execute(() -> {
            dailyActivityDao.addDailyActivity(dailyActivity);
        });
    }

    public void updateDailyActivity(DailyActivity dailyActivity) {
        executorService.execute(() -> {
            dailyActivityDao.updateDailyActivity(dailyActivity);
        });
    }

    public void deleteDailyActivity(DailyActivity dailyActivity) {
        executorService.execute(() -> {
            dailyActivityDao.deleteDailyActivity(dailyActivity);
        });
    }

    public LiveData<List<DailyActivity>> getAllDailyActivities() {
        return dailyActivityDao.getAllDailyActivity();
    }

    //Blood Sugar
    public void insertBloodSugar(BloodSugar bloodSugar) {
        executorService.execute(() -> {
            bloodSugarDao.insertBloodSugar(bloodSugar);
        });
    }

    public void updateBloodSugar(BloodSugar bloodSugar) {
        executorService.execute(() -> {
            bloodSugarDao.updateBloodSugar(bloodSugar);
        });
    }

    public void deleteBloodSugar(BloodSugar bloodSugar) {
        executorService.execute(() -> {
            bloodSugarDao.deleteBloodSugar(bloodSugar);
        });
    }

    public LiveData<List<BloodSugar>> getAllBloodSugars() {
        return bloodSugarDao.getAllBloodSugar();
    }

    //Daily Mood
    public void insertDailyMood(DailyMood dailyMood) {
        executorService.execute(() -> {
            dailyMoodDao.insertDailyMood(dailyMood);
        });
    }

    public void updateDailyMood(DailyMood dailyMood) {
        executorService.execute(() -> {
            dailyMoodDao.updateDailyMood(dailyMood);
        });
    }

    public void deleteDailyMood(DailyMood dailyMood) {
        executorService.execute(() -> {
            dailyMoodDao.deleteDailyMood(dailyMood);
        });
    }

    public LiveData<List<DailyMood>> getAllDailyMoods() {
        return dailyMoodDao.getAllDailyMood();
    }
}

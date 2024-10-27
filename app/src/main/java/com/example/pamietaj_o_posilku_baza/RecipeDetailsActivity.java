package com.example.pamietaj_o_posilku_baza;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pamietaj_o_posilku_baza.database.dao.RecipeDao;
import com.example.pamietaj_o_posilku_baza.database.dao.RecipeIngredientCrossRefDao;

public class RecipeDetailsActivity extends AppCompatActivity {

    private TextView recipeName;
    private RecyclerView ingredientsRecyclerView;
//    private IngredientDetailsAdapter ingredientDetailsAdapter; // Zmieniona nazwa
    private AppDatabase db;
    private RecipeDao recipeDao;
    private RecipeIngredientCrossRefDao crossRefDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Inicjalizacja widoków
        recipeName = findViewById(R.id.recipe_name);
        ingredientsRecyclerView = findViewById(R.id.ingredients_recycler_view);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Pobierz ID przepisu z Intenta
        long recipeId = getIntent().getLongExtra("RECIPE_ID", -1);

        // Inicjalizacja bazy danych
        db = AppDatabase.getInstance(getApplicationContext());
        recipeDao = db.recipeDao();
        crossRefDao = db.recipeIngredientCrossRefDao();

        loadRecipeDetails(recipeId);
    }

    private void loadRecipeDetails(long recipeId) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Handler handler = new Handler(Looper.getMainLooper());
//
//        executor.execute(() -> {
//            // Pobierz przepis z wszystkimi składnikami
//            RecipeWithIngredients recipeWithIngredients = recipeDao.getRecipeWithIngredients(recipeId);
//
//            handler.post(() -> {
//                recipeName.setText(recipeWithIngredients.recipe.getName());
//                ingredientDetailsAdapter = new IngredientDetailsAdapter(recipeWithIngredients.ingredients);
//                ingredientsRecyclerView.setAdapter(ingredientDetailsAdapter);
//            });
//        });
    }

}
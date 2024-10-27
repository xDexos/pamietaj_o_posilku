package com.example.pamietaj_o_posilku_baza;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pamietaj_o_posilku_baza.adapter.RecipeAdapter;
import com.example.pamietaj_o_posilku_baza.database.model.Recipe;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipesActivity extends AppCompatActivity implements RecipeAdapter.OnRecipeClickListener, RecipeAdapter.OnRecipeDeleteListener{

    private AppDatabase db;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = AppDatabase.getInstance(getApplicationContext());

        // Inicjalizacja RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recipesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(this, this);
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> addTestRecipe());

        // Załaduj przepisy
        loadRecipes();
    }

    private void loadRecipes() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Recipe> recipes = db.recipeDao().getAllRecipes();
            runOnUiThread(() -> adapter.setRecipes(recipes));
        });
        executor.shutdown();
    }

    private void addTestRecipe() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                Recipe recipe = new Recipe("Nowy przepis " + System.currentTimeMillis(), "zrób");
                db.recipeDao().insert(recipe);

                // Odśwież listę przepisów
                loadRecipes();

                runOnUiThread(() -> Toast.makeText(RecipesActivity.this,
                        "Przepis dodany!", Toast.LENGTH_SHORT).show());
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(RecipesActivity.this,
                        "Błąd: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
        executor.shutdown();
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        // Tutaj możesz dodać obsługę kliknięcia w przepis
        // np. otworzyć nową aktywność z szczegółami przepisu
        Toast.makeText(this, "Wybrano przepis: " + recipe.getName(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecipeDelete(Recipe recipe) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            db.recipeDao().delete(recipe);
            // Odśwież listę przepisów
            loadRecipes();
            runOnUiThread(() -> Toast.makeText(RecipesActivity.this,
                    "Przepis usunięty!", Toast.LENGTH_SHORT).show());
        });
        executor.shutdown();
    }
}
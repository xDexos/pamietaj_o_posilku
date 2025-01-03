package com.example.application_remake.ingredients;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application_remake.R;
import com.example.application_remake.adapter.IngredientAdapter;
import com.example.application_remake.adapter.OnIngredientCheckedChangeListenerInterface;
import com.example.application_remake.adapter.RecycleViewInterface;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Ingredient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment implements RecycleViewInterface, OnIngredientCheckedChangeListenerInterface {

    private RecipeRepository repository;

    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;

    private FloatingActionButton addIngredientButton;
    private FloatingActionButton ingredientsTipsButton;

    private Dialog dialog;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        repository = new RecipeRepository(this.requireContext());

        {
            addIngredientButton = view.findViewById(R.id.addIngredientButton);
            ingredientsTipsButton = view.findViewById(R.id.ingredientsTipsButton);

            addIngredientButton.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_ingredientsFragment_to_ingredientFormFragment);
            });

        }

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_ingredients_tips);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ingredientsTipsButton.setOnClickListener(v -> {
            dialog.show();
        });

        recyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        addRecipeButton = view.findViewById(R.id.addRecipeButton);
//        addRecipeButton.setOnClickListener( v -> {
//            NavController navController = Navigation.findNavController(v);
//            navController.navigate(R.id.action_recipesFragment_to_recipeFormFragment);
//        });

        ingredientAdapter = new IngredientAdapter(this.requireContext(), new ArrayList<>(), this, this);
        recyclerView.setAdapter(ingredientAdapter);

        // Nasłuchiwanie zmian w bazie danych
        repository.getAllIngredients().observe(getViewLifecycleOwner(), items -> {
            ingredientAdapter.setItems(items);
        });

        return view;

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onEditClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {
        if (position >= 0 && position < ingredientAdapter.ingredientList.size()) {

            Ingredient ingredientToDelete = ingredientAdapter.ingredientList.get(position);
            repository.deleteIngredient(ingredientToDelete);

            // Usuń element z listy i zaktualizuj RecyclerView w głównym wątku
            ingredientAdapter.ingredientList.remove(position);
            ingredientAdapter.notifyItemRemoved(position);
        } else {
            Log.e("RecipesFragment", "Nieprawidłowy indeks: " + position);
        }
    }

    @Override
    public void onIngredientChecked(Ingredient ingredient, boolean isChecked) {
        ingredient.setChecked(isChecked);
        repository.updateIngredient(ingredient);

        // Dodatkowe akcje
        Log.d("IngredientsFragment", "Składnik: " + ingredient.getName() + " zmieniony na: " + isChecked);
    }
}
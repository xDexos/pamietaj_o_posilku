package com.example.application_remake.recipes;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.application_remake.R;
import com.example.application_remake.adapter.IngredientAdapter;
import com.example.application_remake.adapter.RecipeDetailsIngredientAdapter;
import com.example.application_remake.adapter.RecycleViewInterface;
import com.example.application_remake.database.RecipeWithIngredients;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsFragment extends Fragment implements RecycleViewInterface {

    private RecipeRepository repository;

    private RecyclerView recyclerView;
    private RecipeDetailsIngredientAdapter recipeDetailsIngredientAdapter;

    private FloatingActionButton addIngredientFloatingActionButton;
    private Dialog dialog;

    private FloatingActionButton recipeNoteReadButton;
    private Dialog dialog2;

    private FloatingActionButton recipeNoteWriteButton;
    private Dialog dialog3;

    private FloatingActionButton saveRecipeNoteButton;

    private float energyInfo = 0;
    private float proteinsInfo = 0;
    private float fatsInfo = 0;
    private float carbohydratesInfo = 0;
    private float saltInfo = 0;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        repository = new RecipeRepository(this.requireContext());

        recyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recipeDetailsIngredientAdapter = new RecipeDetailsIngredientAdapter(this.requireContext(), new ArrayList<>(), this);

        addIngredientFloatingActionButton = view.findViewById(R.id.showListSummaryButton);
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_recipe_wartosci_odzywcze);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        addIngredientFloatingActionButton.setOnClickListener(v -> {
            TextView energyTextView = dialog.findViewById(R.id.recipeEnergyTextView);
            TextView nutriansTextView = dialog.findViewById(R.id.recipeNutriansTextView);

            StringBuilder energy = new StringBuilder();
            for (Ingredient ingredient : recipeDetailsIngredientAdapter.ingredientList) {
                // Formatowanie z wyrównaniem
                energy.append(String.format("%-15s %3.1f", ingredient.getName(), ingredient.getCalories())).append("\n");
            }
            energy.append(String.format("%-10s %4.1f", "Kalorie:", getEnergyInfo()));

            energyTextView.setTypeface(Typeface.MONOSPACE); // Ustaw czcionkę Monospace
            energyTextView.setText(energy);

            StringBuilder nutrians = new StringBuilder();
            nutrians.append(String.format("%-8s %-6s %-8s %-5s %-3s\n", "Składnik", "Białko", "Tłuszcze", "Węgle", "Sól")); // Nagłówki tabeli
            nutrians.append("--------------------------------\n"); // Linia podziału

            for (Ingredient ingredient : recipeDetailsIngredientAdapter.ingredientList) {
                // Formatowanie wierszy danych
                nutrians.append(String.format(
                        "%-15s %-4.1f %-4.1f %-4.1f %-4.1f\n",
                        ingredient.getName(),
                        ingredient.getProteins(),
                        ingredient.getFats(),
                        ingredient.getCarbohydrates(),
                        ingredient.getSalt()
                ));
            }

// Na końcu dodaj podsumowanie kalorii:
            nutrians.append("--------------------------------\n");

            nutrians.append(String.format("%-4s %-5.1f %-5.1f %-5.1f %-5.1f\n", "Suma: ", getProteinsInfo(), getFatsInfo(), getCarbohydratesInfo(), getSaltInfo()));

            nutriansTextView.setTypeface(Typeface.MONOSPACE); // Czcionka Monospace dla równego wyrównania
            nutriansTextView.setText(nutrians);


            dialog.show();
        });

        return view;
    }

    private float getEnergyInfo() {
        energyInfo = 0;
        for (Ingredient ingredient : recipeDetailsIngredientAdapter.ingredientList) {
            energyInfo += ingredient.getCalories();
        }
        return energyInfo;
    }

    private float getProteinsInfo() {
        proteinsInfo = 0;
        for (Ingredient ingredient : recipeDetailsIngredientAdapter.ingredientList) {
            proteinsInfo += ingredient.getProteins();
        }
        return proteinsInfo;
    }

    private float getFatsInfo() {
        fatsInfo = 0;
        for (Ingredient ingredient : recipeDetailsIngredientAdapter.ingredientList) {
            fatsInfo += ingredient.getFats();
        }
        return fatsInfo;
    }

    private float getCarbohydratesInfo() {
        carbohydratesInfo = 0;
        for (Ingredient ingredient : recipeDetailsIngredientAdapter.ingredientList) {
            carbohydratesInfo += ingredient.getCarbohydrates();
        }
        return carbohydratesInfo;
    }

    private float getSaltInfo() {
        saltInfo = 0;
        for (Ingredient ingredient : recipeDetailsIngredientAdapter.ingredientList) {
            saltInfo += ingredient.getSalt();
        }
        return saltInfo;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView recipeNameTextView = view.findViewById(R.id.detailRecipeName);
        TextView recipePreparationTextView = view.findViewById(R.id.detailRecipePreparation);

        if (getArguments() != null) {
            Long recipeId = getArguments().getLong("recipeId");

            repository.getRecipeById(recipeId).observe(getViewLifecycleOwner(), recipe -> {
                if (recipe != null) {
                    recipeNameTextView.setText(recipe.getName());
                    recipePreparationTextView.setText(recipe.getPreparation());
                }
            });

//            repository.getRecipeWithIngredients(recipeId).observe(getViewLifecycleOwner(), recipeWithIngredients -> {
//                if (recipeWithIngredients != null && recipeWithIngredients.ingredientsEntities != null) {
//                    List<Ingredient> recipeIngredients = recipeWithIngredients.ingredientsEntities;
//
//                    StringBuilder ingredientsText = new StringBuilder();
//                    for (Ingredient ingredient : recipeIngredients) {
//                        ingredientsText.append(ingredient.getName()).append(" ");
//                    }
//
//                    recipeIngredientsTextView.setText(ingredientsText.length() > 0 ?
//                            ingredientsText.toString() : "Brak składników");
//                } else {
//                    recipeIngredientsTextView.setText("Brak składników");
//                }
//            });

            repository.getRecipeWithIngredients(recipeId).observe(getViewLifecycleOwner(), recipeWithIngredients -> {
                if (recipeWithIngredients != null && recipeWithIngredients.ingredientsEntities != null) {
                    List<Ingredient> recipeIngredients = recipeWithIngredients.ingredientsEntities;
                    recipeDetailsIngredientAdapter.setItems(recipeIngredients);
                    recyclerView.setAdapter(recipeDetailsIngredientAdapter);
                }
            });

            recipeNoteReadButton = view.findViewById(R.id.showNoteButton);
            dialog2 = new Dialog(getContext());
            dialog2.setContentView(R.layout.dialog_recipe_note_read);
            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView recipeNoteTextView = dialog2.findViewById(R.id.recipeNoteTextView);

            recipeNoteReadButton.setOnClickListener(v -> {

                repository.getRecipeById(recipeId).observe(getViewLifecycleOwner(), recipe -> {
                            if (recipe != null) {
                                recipeNoteTextView.setText(recipe.getNote());
                            }
                });

                dialog2.show();

            });

            recipeNoteWriteButton = dialog2.findViewById(R.id.editRecipeNoteButton);
            dialog3 = new Dialog(getContext());
            dialog3.setContentView(R.layout.dialog_recipe_note_write);
            dialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            saveRecipeNoteButton = dialog3.findViewById(R.id.saveRecipeNoteButton);

            TextView recipeNoteWriteTextView = dialog3.findViewById(R.id.recipeNoteWriteTextView);

            recipeNoteWriteButton.setOnClickListener(v -> {
                dialog2.dismiss();
                dialog3.show();

                saveRecipeNoteButton.setOnClickListener(v1 -> {
                    repository.setRecipeNote(recipeId, recipeNoteWriteTextView.getText().toString());
                    dialog3.dismiss();
                });
            });
        }

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onEditClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }
}
package com.example.application_remake.list;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application_remake.R;
import com.example.application_remake.adapter.ListDetailsIngredientAdapter;
import com.example.application_remake.adapter.ListRecyclerViewInterface;
import com.example.application_remake.adapter.PickIngredientAdapter;
import com.example.application_remake.adapter.RecycleViewInterface;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.ShoppingListIngredientCrossRef;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListDetailsFragment extends Fragment implements RecycleViewInterface, ListRecyclerViewInterface {

    private RecipeRepository repository;

    private RecyclerView recyclerView;
    private RecyclerView dialogRecyclerView;
    private ListDetailsIngredientAdapter listDetailsIngredientAdapter;
    private PickIngredientAdapter pickIngredientAdapter;

    private FloatingActionButton addIngredientFloatingActionButton;
    private Dialog dialog;

    private FloatingActionButton addIngredientFloatingActionButton2;
    private Dialog dialog2;

    private float energyInfo = 0;
    private float proteinsInfo = 0;
    private float fatsInfo = 0;
    private float carbohydratesInfo = 0;
    private float saltInfo = 0;

    private Spinner spinner;
    private List<String> spinnerList;

    private long listId;

    public ListDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_details, container, false);

        repository = new RecipeRepository(this.requireContext());

        addIngredientFloatingActionButton = view.findViewById(R.id.addListIngredientButton);
        spinner = view.findViewById(R.id.spinner);

        spinnerList = new ArrayList<>();
        spinnerList.add("Wszystkie");
        spinnerList.add("Produkty zbożowe");
        spinnerList.add("Słodziki");
        spinnerList.add("Tłuszcze");
        spinnerList.add("Nabiał");
        spinnerList.add("Produkty zwierzęce");
        spinnerList.add("Ryby");
        spinnerList.add("Warzywa");
        spinnerList.add("Owoce");
        spinnerList.add("Mięso");
        spinnerList.add("Ryby");
        spinnerList.add("Słodycze");
        spinnerList.add("Roślinne");
        spinnerList.add("Rośliny strączkowe");
        spinnerList.add("Przyprawy");
        spinnerList.add("Sosy");
        spinnerList.add("Orzechy");
        spinnerList.add("Suszone owoce");
        spinnerList.add("Inne");

        addIngredientFloatingActionButton2 = view.findViewById(R.id.showListSummaryButton);
        dialog2 = new Dialog(getContext());
        dialog2.setContentView(R.layout.dialog_list_wartosci_odzywcze);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        addIngredientFloatingActionButton2.setOnClickListener(v -> {
            TextView energyTextView = dialog2.findViewById(R.id.listEnergyTextView);
            TextView nutriansTextView = dialog2.findViewById(R.id.listNutriansTextView);

            StringBuilder energy = new StringBuilder();
            for (Ingredient ingredient : listDetailsIngredientAdapter.ingredientList) {
                // Formatowanie z wyrównaniem
                energy.append(String.format("%-15s %3.1f", ingredient.getName(), ingredient.getCalories())).append("\n");
            }
            energy.append(String.format("%-10s %4.1f", "Kalorie:", getEnergyInfo()));

            energyTextView.setTypeface(Typeface.MONOSPACE); // Ustaw czcionkę Monospace
            energyTextView.setText(energy);

            StringBuilder nutrians = new StringBuilder();
            nutrians.append(String.format("%-8s %-6s %-8s %-5s %-3s\n", "Składnik", "Białko", "Tłuszcze", "Węgle", "Sól")); // Nagłówki tabeli
            nutrians.append("--------------------------------\n"); // Linia podziału

            for (Ingredient ingredient : listDetailsIngredientAdapter.ingredientList) {
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


            dialog2.show();
        });

        recyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listDetailsIngredientAdapter = new ListDetailsIngredientAdapter(this.requireContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(listDetailsIngredientAdapter);

        // Ustawienie dialogu dla dodawania posiłków
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_ingredient_picker_list);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialogRecyclerView = dialog.findViewById(R.id.pickIngredientRecyclerView);
        dialogRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pickIngredientAdapter = new PickIngredientAdapter(this.requireContext(), new ArrayList<>(), this);
        dialogRecyclerView.setAdapter(pickIngredientAdapter);

        repository.getAllIngredients().observe(getViewLifecycleOwner(), items -> {
            pickIngredientAdapter.setItems(items);
        });

        addIngredientFloatingActionButton.setOnClickListener(v -> {
            dialog.show();
        });

        sutupTypeSpinner();

        return view;
    }

    private void sutupTypeSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "Wybrany typ skladników: " + item, Toast.LENGTH_SHORT).show();

                // Pobierz składniki po wybranym typie i zaktualizuj RecyclerView
                if (!item.equals("Wszystkie")) {
                    repository.getIngredientsForShoppingList(listId, item).observe(getViewLifecycleOwner(), ingredients -> {
                        if (ingredients != null) {
                            listDetailsIngredientAdapter.setItems(ingredients); // Zaktualizuj dane w adapterze
                        }
                    });
//                } else {
//                    // Jeśli wybrano "Wszystkie", pobierz wszystkie składniki
//                    repository.getAllIngredients().observe(getViewLifecycleOwner(), ingredients -> {
//                        if (ingredients != null) {
//                            listDetailsIngredientAdapter.setItems(ingredients); // Zaktualizuj dane w adapterze
//                        }
//                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private float getEnergyInfo() {
        energyInfo = 0;
        for (Ingredient ingredient : listDetailsIngredientAdapter.ingredientList) {
            energyInfo += ingredient.getCalories();
        }
        return energyInfo;
    }

    private float getProteinsInfo() {
        proteinsInfo = 0;
        for (Ingredient ingredient : listDetailsIngredientAdapter.ingredientList) {
            proteinsInfo += ingredient.getProteins();
        }
        return proteinsInfo;
    }

    private float getFatsInfo() {
        fatsInfo = 0;
        for (Ingredient ingredient : listDetailsIngredientAdapter.ingredientList) {
            fatsInfo += ingredient.getFats();
        }
        return fatsInfo;
    }

    private float getCarbohydratesInfo() {
        carbohydratesInfo = 0;
        for (Ingredient ingredient : listDetailsIngredientAdapter.ingredientList) {
            carbohydratesInfo += ingredient.getCarbohydrates();
        }
        return carbohydratesInfo;
    }

    private float getSaltInfo() {
        saltInfo = 0;
        for (Ingredient ingredient : listDetailsIngredientAdapter.ingredientList) {
            saltInfo += ingredient.getSalt();
        }
        return saltInfo;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView listNameTextView = view.findViewById(R.id.detailListName);

        if (getArguments() != null) {
            listId = getArguments().getLong("listId");

            repository.getShoppingListById(listId).observe(getViewLifecycleOwner(), shoppingList -> {
                if (shoppingList != null) {
                    listNameTextView.setText(shoppingList.getName());
                }
            });

            repository.getShoppingListWithIngredients(listId).observe(getViewLifecycleOwner(), ingredients -> {
                if (ingredients != null) {
                    if (ingredients.ingredients != null && !ingredients.ingredients.isEmpty()) {
                        listDetailsIngredientAdapter.setItems(ingredients.ingredients);
                        recyclerView.setAdapter(listDetailsIngredientAdapter);
                    }

                }
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
        Ingredient ingredient = listDetailsIngredientAdapter.ingredientList.get(position);
        repository.removeIngredientFromShoppingList(listId, ingredient.getId());

    }

    @Override
    public void onListItemClick(int position) {
        if (position >= 0 && position < pickIngredientAdapter.ingredientList.size()) {
            // Pobierz element kliknięty w allIngredientsAdapter
            Ingredient ingredient = pickIngredientAdapter.ingredientList.get(position);

            // Usuń z pierwszego adaptera
            pickIngredientAdapter.ingredientList.remove(position);

            // Dodaj do drugiego adaptera
            listDetailsIngredientAdapter.ingredientList.add(ingredient);

            // Zaktualizuj oba RecyclerView
            pickIngredientAdapter.notifyItemRemoved(position);
            listDetailsIngredientAdapter.notifyItemInserted(listDetailsIngredientAdapter.ingredientList.size() - 1);

            repository.addIngredientToShoppingList(listId, ingredient.getId());
        } else {
            Log.e("RecipeFormFragment", "Nieprawidłowy indeks: " + position);
        }
    }
}
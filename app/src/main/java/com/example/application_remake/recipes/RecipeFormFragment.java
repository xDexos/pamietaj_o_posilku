package com.example.application_remake.recipes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.application_remake.R;
import com.example.application_remake.adapter.ChoosedIngredientAdapter;
import com.example.application_remake.adapter.RecipeDetailsIngredientAdapter;
import com.example.application_remake.adapter.RecycleViewInterface;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeFormFragment extends Fragment implements RecycleViewInterface {

    private RecipeRepository repository;

    List<Ingredient> choosedIngredients;

    private RecyclerView ingredientRecyclerView;
    private RecyclerView choosedIngredientRecyclerView;
    private RecipeDetailsIngredientAdapter allIngredientsAdapter;
    private ChoosedIngredientAdapter choosedIngredientsAdapter;

    private EditText addRecipeName;
    private EditText addRecipePreparation;
    private Button submitAddRecipeButton;
    private boolean isValid;

    public RecipeFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_form, container, false);

        repository = new RecipeRepository(this.requireContext());

        // ustawianie pól formularza
        {
            addRecipeName = view.findViewById(R.id.addRecipeName);
            addRecipePreparation = view.findViewById(R.id.addRecipePreparation);
            submitAddRecipeButton = view.findViewById(R.id.submitAddRecipeButton);
        }

        // ustawianie recycler views
        {
            // Inicjalizacja listy wybranych składników
            choosedIngredients = new ArrayList<>();

            ingredientRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
            ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            choosedIngredientRecyclerView = view.findViewById(R.id.choosedIngredientsRecyclerView);
            choosedIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            allIngredientsAdapter = new RecipeDetailsIngredientAdapter(this.requireContext(), new ArrayList<>(), this);
            choosedIngredientsAdapter = new ChoosedIngredientAdapter(this.requireContext(), choosedIngredients, this);

            ingredientRecyclerView.setAdapter(allIngredientsAdapter);
            choosedIngredientRecyclerView.setAdapter(choosedIngredientsAdapter);

            // Nasłuchiwanie zmian w bazie danych
            repository.getAllIngredients().observe(getViewLifecycleOwner(), items -> {
                allIngredientsAdapter.setItems(items);
            });
        }

        // walidacja danych i dodawanie nowego przepisu
        {

//            submitAddRecipeButton.setOnClickListener( v -> {
//
//                String inputText = addRecipeName.getText().toString().trim();
//                String inputText2 = addRecipePreparation.getText().toString().trim();
//
//                if (TextUtils.isEmpty(inputText)) {
//                    // Pokaż komunikat, że pole jest wymagane
//                    Toast.makeText(getContext(), "Pole tekstowe jest wymagane!", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.isEmpty(inputText2)) {
//                    // Pokaż komunikat, że pole jest wymagane
//                    Toast.makeText(getContext(), "Pole tekstowe jest wymagane!", Toast.LENGTH_SHORT).show();
//                } else {
//                    // Kontynuuj działanie
//                    Toast.makeText(getContext(), "Dane poprawne: " + inputText, Toast.LENGTH_SHORT).show();
//
//                    String name = addRecipeName.getText().toString();
//                    String preparation = addRecipePreparation.getText().toString();
//
//                    Recipe recipe = new Recipe();
//                    recipe.setName(name);
//                    recipe.setPreparation(preparation);
//
//                    List<Ingredient> ingredientsToAdd = new ArrayList<>();
//
//                    for (Ingredient ingredient : choosedIngredients) {
//                        Ingredient ing = new Ingredient(ingredient.getName(), ingredient.getType());
//                        ingredientsToAdd.add(ing);
//                    }
//
//                    repository.insertRecipeWithIngredients(recipe, ingredientsToAdd);
//
//                    NavController navController = Navigation.findNavController(v);
//                    navController.navigate(R.id.action_recipeFormFragment_to_recipesFragment);
//                }
//
//            });

            submitAddRecipeButton.setOnClickListener(v -> {
                String inputText = addRecipeName.getText().toString().trim();
                String inputText2 = addRecipePreparation.getText().toString().trim();

                if (TextUtils.isEmpty(inputText)) {
                    Toast.makeText(getContext(), "Pole tekstowe jest wymagane!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inputText2)) {
                    Toast.makeText(getContext(), "Pole tekstowe jest wymagane!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Dane poprawne: " + inputText, Toast.LENGTH_SHORT).show();

                    String name = addRecipeName.getText().toString();
                    String preparation = addRecipePreparation.getText().toString();

                    Recipe recipe = new Recipe();
                    recipe.setName(name);
                    recipe.setPreparation(preparation);

                    // Użyj bezpośrednio wybranych składników zamiast tworzyć nowe
                    repository.insertRecipeWithIngredients2(recipe, choosedIngredients);

                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_recipeFormFragment_to_recipesFragment);
                }
            });

        }

        return view;
    }

    @Override
    public void onItemClick(int position) {
        if (position >= 0 && position < allIngredientsAdapter.ingredientList.size()) {
            // Pobierz element kliknięty w allIngredientsAdapter
            Ingredient ingredient = allIngredientsAdapter.ingredientList.get(position);

            // Usuń z pierwszego adaptera
            allIngredientsAdapter.ingredientList.remove(position);

            // Dodaj do drugiego adaptera
            choosedIngredientsAdapter.ingredientList.add(ingredient);

            // Zaktualizuj oba RecyclerView
            allIngredientsAdapter.notifyItemRemoved(position);
            choosedIngredientsAdapter.notifyItemInserted(choosedIngredientsAdapter.ingredientList.size() - 1);
        } else {
            Log.e("RecipeFormFragment", "Nieprawidłowy indeks: " + position);
        }
    }

    @Override
    public void onEditClick(int position) {
        if (position >= 0 && position < allIngredientsAdapter.ingredientList.size()) {

//            //Kliknięto element w "choosedIngredients" - przenosimy go do "allIngredients"
//            Ingredient ingredient = choosedIngredientsAdapter.ingredientList.get(position);
//
//            choosedIngredientsAdapter.ingredientList.remove(position);
//            allIngredientsAdapter.ingredientList.add(ingredient);
//
//            // Aktualizuj adaptery
//            choosedIngredientsAdapter.notifyItemRemoved(position);
//            allIngredientsAdapter.notifyItemInserted(allIngredientsAdapter.ingredientList.size() - 1);

        } else {
            Log.e("RecipeFormFragment", "Nieprawidłowy indeks: " + position);
        }
    }


    @Override
    public void onDeleteClick(int position) {

    }
}
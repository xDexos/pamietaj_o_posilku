package com.example.application_remake.ingredients;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.application_remake.R;
import com.example.application_remake.adapter.ChoosedIngredientAdapter;
import com.example.application_remake.adapter.RecipeDetailsIngredientAdapter;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.Recipe;

import java.util.ArrayList;

public class IngredientFormFragment extends Fragment {

    private RecipeRepository repository;

    private EditText addIngredientName;
    private EditText addIngredientType;
    private ImageButton submitAddIngredientButton;

    public IngredientFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient_form, container, false);

        repository = new RecipeRepository(this.requireContext());

        // ustawianie pól formularza
        {
            addIngredientName = view.findViewById(R.id.ingredientName);
            addIngredientType = view.findViewById(R.id.ingredientType);
            submitAddIngredientButton = view.findViewById(R.id.addIngredientButton);
        }

        submitAddIngredientButton.setOnClickListener(v -> {
            String inputText = addIngredientName.getText().toString().trim();
            String inputText2 = addIngredientType.getText().toString().trim();
            if (TextUtils.isEmpty(inputText)) {
                // Pokaż komunikat, że pole jest wymagane
                Toast.makeText(getContext(), "Pole tekstowe jest wymagane!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inputText2)) {
                // Pokaż komunikat, że pole jest wymagane
                Toast.makeText(getContext(), "Pole tekstowe jest wymagane!", Toast.LENGTH_SHORT).show();
            } else {
                // Kontynuuj działanie
                Toast.makeText(getContext(), "Dane poprawne: " + inputText, Toast.LENGTH_SHORT).show();
                String name = addIngredientName.getText().toString();
                String type = addIngredientType.getText().toString();
                Ingredient ing = new Ingredient(name, type, false ,0, 0, 0, 0, 0);
                repository.insertIngredient(ing);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_ingredientFormFragment_to_ingredientsFragment);
            }
        });

        return view;
    }
}
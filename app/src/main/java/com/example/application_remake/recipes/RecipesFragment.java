package com.example.application_remake.recipes;

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
import com.example.application_remake.adapter.RecipeAdapter;
import com.example.application_remake.adapter.RecycleViewInterface;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecipesFragment extends Fragment implements RecycleViewInterface {

    private RecipeRepository repository;

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private FloatingActionButton addRecipeButton;

    private FloatingActionButton recipesTipsButton;
    private Dialog dialog;

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        repository = new RecipeRepository(this.requireContext());

        recyclerView = view.findViewById(R.id.recipesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recipesTipsButton = view.findViewById(R.id.recipesTipsButton);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_recipes_tips);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        recipesTipsButton.setOnClickListener(v -> {
            dialog.show();
        });

        addRecipeButton = view.findViewById(R.id.addRecipeButton);
        addRecipeButton.setOnClickListener( v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_recipesFragment_to_recipeFormFragment);
        });

        adapter = new RecipeAdapter(this.requireContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        // Nasłuchiwanie zmian w bazie danych
        repository.getAllRecipes().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        return view;
    }

    @Override
    public void onItemClick(int position) {
        NavController navController = Navigation.findNavController(requireView());

        Bundle bundle = new Bundle();
        bundle.putString("testMessage", "testMessage");
        bundle.putLong("recipeId", adapter.recipeList.get(position).getId());

        navController.navigate(R.id.action_recipesFragment_to_recipeDetailsFragment, bundle);
    }

    @Override
    public void onEditClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {
        if (position >= 0 && position < adapter.recipeList.size()) {
            // Usuń z bazy danych
            Recipe recipeToDelete = adapter.recipeList.get(position);
            repository.deleteRecipe(recipeToDelete);

            // Usuń element z adaptera
            adapter.recipeList.remove(position);
            adapter.notifyItemRemoved(position);
        } else {
            Log.e("RecipesFragment", "Nieprawidłowy indeks: " + position);
        }
    }
}
package com.example.pamietaj_o_posilku_baza.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pamietaj_o_posilku_baza.R;
import com.example.pamietaj_o_posilku_baza.database.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipes = new ArrayList<>();
    private OnRecipeClickListener listener;
    private OnRecipeDeleteListener onRecipeDeleteListener;

    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }

    public interface OnRecipeDeleteListener{
        void  onRecipeDelete(Recipe recipe);
    }

    public RecipeAdapter(OnRecipeClickListener listener, OnRecipeDeleteListener onRecipeDeleteListener) {
        this.listener = listener;
        this.onRecipeDeleteListener = onRecipeDeleteListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);

        // Ustawienie akcji kliknięcia na przycisku usuwania
        holder.deleteButton.setOnClickListener(v -> {
            if (onRecipeDeleteListener != null) {
                onRecipeDeleteListener.onRecipeDelete(recipe);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRecipeName;
        private Button deleteButton; // Przycisk do usówania

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            deleteButton = itemView.findViewById(R.id.deleteButton); // Znajdź przycisk

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onRecipeClick(recipes.get(position));
                }
            });
        }

        public void bind(Recipe recipe) {
            tvRecipeName.setText(recipe.getName());
        }
    }
}
package com.example.application_remake.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application_remake.R;
import com.example.application_remake.database.table.Recipe;

import java.util.List;

public class PickRecipeAdapter extends RecyclerView.Adapter<PickRecipeAdapter.PickRecipeViewHolder> {

    private final RecycleViewInterface recycleViewInterface;

    Context context;
    public List<Recipe> recipeList;


    public PickRecipeAdapter(Context context, List<Recipe> recipeList, RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.recipeList = recipeList;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public PickRecipeAdapter.PickRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_calendar_recipe, parent, false);
        return new PickRecipeAdapter.PickRecipeViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PickRecipeAdapter.PickRecipeViewHolder holder, int position) {
        holder.recipeName.setText(recipeList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class PickRecipeViewHolder extends RecyclerView.ViewHolder {

//        ImageView imageView;
        TextView recipeName;
//        ImageButton deleteButton;

        public PickRecipeViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

//            imageView = itemView.findViewById(R.id.imageView);
            recipeName = itemView.findViewById(R.id.recipeName);
//            deleteButton = itemView.findViewById(R.id.deleteButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recycleViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onItemClick(position);
                        }
                    }
                }
            });

//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (recycleViewInterface != null) {
//                        int position = getAdapterPosition();
//
//                        if (position != RecyclerView.NO_POSITION) {
//                            recycleViewInterface.onDeleteClick(position);
//                        }
//                    }
//                }
//            });

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Recipe> recipes) {
        this.recipeList = recipes;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateRecipes(List<Recipe> recipes) {
        this.recipeList = recipes;
        notifyDataSetChanged();
    }
}
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
import com.example.application_remake.database.table.Ingredient;

import java.util.List;

public class ChoosedIngredientAdapter extends RecyclerView.Adapter<ChoosedIngredientAdapter.ChoosedIngredientViewHolder>{

    private final RecycleViewInterface recycleViewInterface;

    Context context;
    public List<Ingredient> ingredientList;


    public ChoosedIngredientAdapter(Context context, List<Ingredient> ingredientList, RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public ChoosedIngredientAdapter.ChoosedIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recipe_details_ingredient, parent, false);
        return new ChoosedIngredientAdapter.ChoosedIngredientViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoosedIngredientAdapter.ChoosedIngredientViewHolder holder, int position) {
        holder.ingredientName.setText(ingredientList.get(position).getName());
        holder.ingredientType.setText(ingredientList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class ChoosedIngredientViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView ingredientName;
        TextView ingredientType;
        ImageButton deleteButton;

        public ChoosedIngredientViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            ingredientType = itemView.findViewById(R.id.ingredientType);
            deleteButton = itemView.findViewById(R.id.deleteButton);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (recycleViewInterface != null) {
//                        int position = getAdapterPosition();
//
//                        if (position != RecyclerView.NO_POSITION) {
//                            recycleViewInterface.onItemClick(position);
//                        }
//                    }
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recycleViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onEditClick(position);
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
    public void setItems(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateRecipes(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
        notifyDataSetChanged();
    }

//    public void updateList(List<Ingredient> newList) {
//        this.ingredientList = newList;
//        notifyDataSetChanged(); // Odświeża cały RecyclerView
//    }
}

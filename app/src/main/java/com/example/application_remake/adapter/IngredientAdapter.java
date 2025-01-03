//package com.example.application_remake.adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.application_remake.R;
//import com.example.application_remake.database.table.Ingredient;
//
//import java.util.List;
//
//public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{
//
//    private final RecycleViewInterface recycleViewInterface;
//    private final OnIngredientCheckedChangeListenerInterface listener;
//
//    Context context;
//    public List<Ingredient> ingredientList;
//
//
//    public IngredientAdapter(Context context, List<Ingredient> ingredientList, RecycleViewInterface recycleViewInterface, OnIngredientCheckedChangeListenerInterface listener) {
//        this.context = context;
//        this.ingredientList = ingredientList;
//        this.recycleViewInterface = recycleViewInterface;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.item_ingredient, parent, false);
//        return new IngredientAdapter.IngredientViewHolder(view, recycleViewInterface);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull IngredientAdapter.IngredientViewHolder holder, int position) {
//        holder.ingredientName.setText(ingredientList.get(position).getName());
//        holder.checkBox.setChecked(ingredientList.get(position).isChecked());
//        holder.ingredientType.setText(ingredientList.get(position).getType());
//
//        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            ingredientList.get(position).setChecked(isChecked);
//            listener.onIngredientChecked(ingredientList.get(position), isChecked);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return ingredientList.size();
//    }
//
//    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView imageView;
//        CheckBox checkBox;
//        TextView ingredientName;
//        TextView ingredientType;
//        ImageButton deleteButton;
//
//        public IngredientViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
//            super(itemView);
//
//            imageView = itemView.findViewById(R.id.imageView);
//            checkBox = itemView.findViewById(R.id.checkBox);
//            ingredientName = itemView.findViewById(R.id.ingredientName);
//            ingredientType = itemView.findViewById(R.id.ingredientType);
//            deleteButton = itemView.findViewById(R.id.deleteButton);
//
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
//
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    public void setItems(List<Ingredient> ingredients) {
//        this.ingredientList = ingredients;
//        notifyDataSetChanged();
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    public void updateRecipes(List<Ingredient> ingredients) {
//        this.ingredientList = ingredients;
//        notifyDataSetChanged();
//    }
//}

package com.example.application_remake.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application_remake.R;
import com.example.application_remake.database.table.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private final RecycleViewInterface recycleViewInterface;
    private final OnIngredientCheckedChangeListenerInterface listener;

    private final Context context;
    public List<Ingredient> ingredientList;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList,
                             RecycleViewInterface recycleViewInterface,
                             OnIngredientCheckedChangeListenerInterface listener) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.recycleViewInterface = recycleViewInterface;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        // Pobierz aktualny składnik
        Ingredient ingredient = ingredientList.get(position);

        // Ustaw dane w widoku
        holder.ingredientName.setText(ingredient.getName());
        holder.ingredientType.setText(ingredient.getType());

        // Usuń poprzedni listener, aby uniknąć problemów z recyklingiem
        holder.checkBox.setOnCheckedChangeListener(null);

        // Ustaw stan CheckBox na podstawie modelu danych
        holder.checkBox.setChecked(ingredient.isChecked());

        // Dodaj nowy listener dla CheckBox
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Zaktualizuj model danych
            ingredient.setChecked(isChecked);

            // Powiadom listener z fragmentu
            if (listener != null) {
                listener.onIngredientChecked(ingredient, isChecked);
            }
        });

        // Obsługa przycisku usuń
        holder.deleteButton.setOnClickListener(v -> {
            if (recycleViewInterface != null && position != RecyclerView.NO_POSITION) {
                recycleViewInterface.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        CheckBox checkBox;
        TextView ingredientName;
        TextView ingredientType;
        ImageButton deleteButton;

        public IngredientViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            checkBox = itemView.findViewById(R.id.checkBox);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            ingredientType = itemView.findViewById(R.id.ingredientType);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            // Obsługa kliknięcia na przycisk usuń
            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (recycleViewInterface != null && position != RecyclerView.NO_POSITION) {
                    recycleViewInterface.onDeleteClick(position);
                }
            });
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
        notifyDataSetChanged();
    }
}

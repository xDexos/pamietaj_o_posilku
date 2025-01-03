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
import com.example.application_remake.database.table.ShoppingList;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private final RecycleViewInterface recycleViewInterface;

    Context context;
    public List<ShoppingList> shoppingListList;


    public ListAdapter(Context context, List<ShoppingList> shoppingListList, RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.shoppingListList = shoppingListList;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ListAdapter.ListViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
        holder.nazwaListyTextView.setText(shoppingListList.get(position).getName());
        holder.dataListyTextView.setText(shoppingListList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return shoppingListList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        TextView nazwaListyTextView;
        TextView dataListyTextView;;
        ImageButton deleteButton;

        public ListViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            nazwaListyTextView = itemView.findViewById(R.id.nazwaListyTextView);
            dataListyTextView = itemView.findViewById(R.id.dataListyTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);

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

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recycleViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<ShoppingList> shoppingLists) {
        this.shoppingListList = shoppingLists;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateRecipes(List<ShoppingList> shoppingLists) {
        this.shoppingListList.clear();
        this.shoppingListList.addAll(shoppingLists);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < shoppingListList.size()) {
            shoppingListList.remove(position);
            notifyItemRemoved(position);
        }
    }

}

package com.example.pamietajoposilku;// PrzepisyAdapter.java
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PrzepisyRecycleViewAdapter extends RecyclerView.Adapter<PrzepisyRecycleViewAdapter.PrzepisViewHolder>{

    private ArrayList<Przepis> _przepisyArrayList;

    public PrzepisyRecycleViewAdapter(ArrayList<Przepis> przepisyArrayList) {
        this._przepisyArrayList = przepisyArrayList;
    }

    @NonNull
    @Override
    public PrzepisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_przepis, parent, false);
        return new PrzepisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrzepisViewHolder holder, int position) {
        Przepis przepis = _przepisyArrayList.get(position);
        holder.nazwaTextView.setText(przepis.getRecipeName());



    }

    @Override
    public int getItemCount() {
        return _przepisyArrayList.size();
    }

    public static class PrzepisViewHolder extends RecyclerView.ViewHolder {
        TextView nazwaTextView;

        public PrzepisViewHolder(View itemView) {
            super(itemView);
            nazwaTextView = itemView.findViewById(R.id.tvPrzepisNazwa);

        }
    }
}
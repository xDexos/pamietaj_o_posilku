package com.example.pamietajoposilku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SkladnikiRecyclerViewAdapter extends RecyclerView.Adapter<SkladnikiRecyclerViewAdapter.MyViewHolder> {

    private List<Ingredient> _ingredientList = new ArrayList<>();

    public SkladnikiRecyclerViewAdapter(List<Ingredient> ingredientList){
        this._ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public SkladnikiRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_skladnik, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkladnikiRecyclerViewAdapter.MyViewHolder holder, int position) {
        Ingredient ingredient = _ingredientList.get(position);
        holder.tvSkladnikNazwa.setText(ingredient.get_ingredientName());
        holder.tvSkladnikIlosc.setText(String.valueOf(ingredient.get_ingredientAmount()));
        holder.tvSkladnikJednostka.setText(ingredient.get_ingredientUnit());
    }

    @Override
    public int getItemCount() {
        return _ingredientList.size();
    }

    public void dodajSkladnik(Ingredient ingredient){
        _ingredientList.add(ingredient);
        notifyItemInserted(_ingredientList.size() - 1);
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvSkladnikNazwa;
        TextView tvSkladnikIlosc;
        TextView tvSkladnikJednostka;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSkladnikNazwa = itemView.findViewById(R.id.tvSkladnikNazwa);
            tvSkladnikIlosc = itemView.findViewById(R.id.tvSkladnikIlosc);
            tvSkladnikJednostka = itemView.findViewById(R.id.tvSkladnikJednostka);

        }
    }

}

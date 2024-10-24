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

    private List<Skladnik> _skladnikList = new ArrayList<>();

    public SkladnikiRecyclerViewAdapter(List<Skladnik> skladnikList){
        this._skladnikList = skladnikList;
    }

    @NonNull
    @Override
    public SkladnikiRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_skladnik, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkladnikiRecyclerViewAdapter.MyViewHolder holder, int position) {
        Skladnik skladnik = _skladnikList.get(position);
        holder.tvSkladnikNazwa.setText(skladnik.get_ingredientName());
        holder.tvSkladnikIlosc.setText(String.valueOf(skladnik.get_ingredientAmount()));
        holder.tvSkladnikJednostka.setText(skladnik.get_ingredientUnit());
    }

    @Override
    public int getItemCount() {
        return _skladnikList.size();
    }

    public void dodajSkladnik(Skladnik skladnik){
        _skladnikList.add(skladnik);
        notifyItemInserted(_skladnikList.size() - 1);
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

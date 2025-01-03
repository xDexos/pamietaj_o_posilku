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
import com.example.application_remake.database.table.PlannedMeal;
import com.example.application_remake.database.table.Recipe;

import java.util.List;

public class CalendarRecipeAdapter extends RecyclerView.Adapter<CalendarRecipeAdapter.CalendarRecipeViewHolder> {

    private final CalendarRecyclerViewInterface calendarRecyclerViewInterface;

    Context context;
    public List<PlannedMeal> mealRecipeList;


    public CalendarRecipeAdapter(Context context, List<PlannedMeal> mealRecipeList, CalendarRecyclerViewInterface calendarRecyclerViewInterface) {
        this.context = context;
        this.mealRecipeList = mealRecipeList;
        this.calendarRecyclerViewInterface = calendarRecyclerViewInterface;
    }

    @NonNull
    @Override
    public CalendarRecipeAdapter.CalendarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_calendar_planned_meal, parent, false);
        return new CalendarRecipeAdapter.CalendarRecipeViewHolder(view, calendarRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarRecipeAdapter.CalendarRecipeViewHolder holder, int position) {
        holder.mealName.setText(mealRecipeList.get(position).getName());

        if (mealRecipeList.get(position).getType() == 0) {
            holder.mealType.setText("Śniadanie");
        } else if (mealRecipeList.get(position).getType() == 1) {
            holder.mealType.setText("Obiad");
        } else if (mealRecipeList.get(position).getType() == 2) {
            holder.mealType.setText("Kolacja");
        }

        int hour = mealRecipeList.get(position).getHour();
        int minute = mealRecipeList.get(position).getMinute();

        holder.mealTime.setText(hour + ":" + minute);
    }

    @Override
    public int getItemCount() {
        return mealRecipeList.size();
    }

    public static class CalendarRecipeViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        TextView mealType;
        TextView mealTime;
        ImageButton deleteButton;

        public CalendarRecipeViewHolder(@NonNull View itemView, CalendarRecyclerViewInterface calendarRecyclerViewInterface) {
            super(itemView);

            mealName = itemView.findViewById(R.id.mealName);
            mealType = itemView.findViewById(R.id.mealType);
            mealTime = itemView.findViewById(R.id.mealTimeText);
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

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (calendarRecyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                             calendarRecyclerViewInterface.onCalendarItemDeleteClick(position);
                        }
                    }
                }
            });

        }
    }

//    @SuppressLint("NotifyDataSetChanged")
//    public void setItems(List<PlannedMeal> plannedMeals) {
//        this.mealRecipeList = plannedMeals;
//        notifyDataSetChanged();
//    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateRecipes(List<PlannedMeal> plannedMeals) {
        this.mealRecipeList = plannedMeals;
        notifyDataSetChanged();
    }

    public void setItems(List<PlannedMeal> items) {
        this.mealRecipeList.clear();
        this.mealRecipeList.addAll(items);
        notifyDataSetChanged();
    }

}

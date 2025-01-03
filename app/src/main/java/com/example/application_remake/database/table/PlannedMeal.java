package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "PlannedMeals")
public class PlannedMeal {

    @ColumnInfo(name = "meal_id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "meal_recipe_id")
    public long recipeId;

    @ColumnInfo(name = "meal_name")
    public String name;

    @ColumnInfo(name = "meal_date")
    public String date;

    @ColumnInfo(name = "meal_time_hour")
    public int hour;

    @ColumnInfo(name = "meal_time_minute")
    public int minute;

    @ColumnInfo(name = "meal_type")
    public int type; // 0 - śniadanie, 1 - obiad, 2 - kolacja

    public PlannedMeal() {

    }

    @Ignore
    public PlannedMeal(long recipeId, String name, String date, int hour, int minute, int type) {
        this.recipeId = recipeId;
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

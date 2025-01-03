package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.PlannedMeal;

import java.util.List;

@Dao
public interface PlannedMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addPlannedMeal(PlannedMeal plannedMeal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addPlannedMeals(List<PlannedMeal> plannedMeals);

    @Update
    public void updatePlannedMeal(PlannedMeal plannedMeal);

    @Delete
    public void deletePlannedMeal(PlannedMeal plannedMeal);

    @Delete
    public void deletePlannedMeals(List<PlannedMeal> plannedMeals);

    @Query("DELETE FROM plannedmeals WHERE meal_id = :planned_meal_id")
    public void deletePlannedMealById(long planned_meal_id);

    @Query("SELECT * FROM plannedmeals")
    public LiveData<List<PlannedMeal>> getAllPlannedMeals();

    @Query("SELECT * FROM plannedmeals WHERE meal_id = :planned_meal_id")
    public LiveData<PlannedMeal> getPlannedMealById(long planned_meal_id);

    @Query("SELECT * FROM plannedmeals WHERE meal_date = :meal_date")
    public LiveData<List<PlannedMeal>> getPlannedMealsByDate(String meal_date);

    @Query("SELECT * FROM plannedmeals WHERE meal_date = :meal_date")
    public List<PlannedMeal> getPlannedMealsByDate2(String meal_date);

}

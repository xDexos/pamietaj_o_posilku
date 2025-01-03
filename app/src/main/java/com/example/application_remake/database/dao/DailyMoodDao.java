package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.DailyMood;

import java.util.List;

@Dao
public interface DailyMoodDao {

    @Insert
    void insertDailyMood(DailyMood dailyMood);

    @Update
    void updateDailyMood(DailyMood dailyMood);

    @Delete
    void deleteDailyMood(DailyMood dailyMood);

    @Query("SELECT * FROM daily_mood")
    LiveData<List<DailyMood>> getAllDailyMood();
}

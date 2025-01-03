package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.DailyActivity;

import java.util.List;

@Dao
public interface DailyActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addDailyActivity(DailyActivity dailyActivity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateDailyActivity(DailyActivity dailyActivity);

    @Delete
    public void deleteDailyActivity(DailyActivity dailyActivity);

    @Query("SELECT * FROM dailyactivity")
    public LiveData<List<DailyActivity>> getAllDailyActivity();
}

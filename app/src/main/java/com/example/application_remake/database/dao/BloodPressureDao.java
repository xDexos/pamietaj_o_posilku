package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.BloodPressure;

import java.util.List;

@Dao
public interface BloodPressureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addBloodPressure(BloodPressure bloodPressure);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateBloodPressure(BloodPressure bloodPressure);

    @Delete
    public void deleteBloodPressure(BloodPressure bloodPressure);

    @Query("SELECT * FROM bloodpressure")
    public LiveData<List<BloodPressure>> getAllBloodPressure();
}

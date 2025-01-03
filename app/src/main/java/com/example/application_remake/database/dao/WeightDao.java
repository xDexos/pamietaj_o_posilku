package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.Weight;

import java.util.List;

@Dao
public interface WeightDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addWeight(Weight weight);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateWeight(Weight weight);

    @Delete
    public void deleteWeight(Weight weight);

    @Query("SELECT * FROM weight")
    public LiveData<List<Weight>> getAllWeight();
}

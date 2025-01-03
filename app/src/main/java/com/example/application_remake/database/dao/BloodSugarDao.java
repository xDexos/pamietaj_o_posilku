package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.BloodSugar;

import java.util.List;

@Dao
public interface BloodSugarDao {

    @Insert
    void insertBloodSugar(BloodSugar bloodSugar);

    @Update
    void updateBloodSugar(BloodSugar bloodSugar);

    @Delete
    void deleteBloodSugar(BloodSugar bloodSugar);

    @Query("SELECT * FROM blood_sugar")
    LiveData<List<BloodSugar>> getAllBloodSugar();

}

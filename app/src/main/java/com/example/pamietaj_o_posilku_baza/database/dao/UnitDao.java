package com.example.pamietaj_o_posilku_baza.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pamietaj_o_posilku_baza.database.model.Unit;

import java.util.List;

@Dao
public interface UnitDao {
    @Insert
    long insert(Unit unit);

    @Query("SELECT * FROM units")
    List<Unit> getAllUnits();

    @Query("SELECT * FROM units WHERE unitId = :id")
    Unit getUnitById(int id);
}
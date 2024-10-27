package com.example.pamietaj_o_posilku_baza.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "units")
public class Unit {
    @PrimaryKey(autoGenerate = true)
    private int unitId;
    private String name;

    // Constructor
    public Unit(String name) {
        this.name = name;
    }

    // Getters and Setters
    public int getUnitId() { return unitId; }
    public void setUnitId(int unitId) { this.unitId = unitId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
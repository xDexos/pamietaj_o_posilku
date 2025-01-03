package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "blood_sugar")
public class BloodSugar {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "blood_sugar_value")
    private float value;

    public BloodSugar() {
    }

    @Ignore
    public BloodSugar(int id, float value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

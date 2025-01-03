package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "BloodPressure")
public class BloodPressure {

    @ColumnInfo(name = "blood_pressure_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "blood_pressure_value_1")
    private int blood_pressure_value_1;

    @ColumnInfo(name = "blood_pressure_value_2")
    private int blood_pressure_value_2;

    public BloodPressure() {
    }

    @Ignore
    public BloodPressure(int blood_pressure_value_1, int blood_pressure_value_2) {
        this.blood_pressure_value_1 = blood_pressure_value_1;
        this.blood_pressure_value_2 = blood_pressure_value_2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBlood_pressure_value_1() {
        return blood_pressure_value_1;
    }

    public void setBlood_pressure_value_1(int blood_pressure_value_1) {
        this.blood_pressure_value_1 = blood_pressure_value_1;
    }

    public int getBlood_pressure_value_2() {
        return blood_pressure_value_2;
    }

    public void setBlood_pressure_value_2(int blood_pressure_value_2) {
        this.blood_pressure_value_2 = blood_pressure_value_2;
    }
}

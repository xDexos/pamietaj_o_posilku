package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Weight")
public class Weight {

    @ColumnInfo(name = "weight_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "weight_value")
    private float weight;

    public Weight() {
    }

    public long getId() {
        return id;
    }

    @Ignore
    public Weight(float weight) {
        this.weight = weight;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recipes")
public class Recipe {

    @ColumnInfo(name = "recipe_id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "recipe_name")
    public String name;

    @ColumnInfo(name = "recipe_preparation")
    public String preparation;

    @ColumnInfo(name = "recipe_note")
    public String note;

    public Recipe() {

    }

    @Ignore
    public Recipe(String name, String preparation, String note) {
        this.name = name;
        this.preparation = preparation;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

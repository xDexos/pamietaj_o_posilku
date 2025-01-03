package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Ingredients")
public class Ingredient {

    @ColumnInfo(name = "ingredient_id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "ingredient_name")
    public String name;

    @ColumnInfo(name = "ingredient_type")
    public String type;

    @ColumnInfo(name = "ingredient_checked")
    public boolean isChecked;

    @ColumnInfo(name = "ingredient_calories")
    public float calories; // Kalorie na 100 g

    @ColumnInfo(name = "ingredient_proteins")
    public float proteins; // Białko na 100 g

    @ColumnInfo(name = "ingredient_fats")
    public float fats; // Tłuszcze na 100 g

    @ColumnInfo(name = "ingredient_carbohydrates")
    public float carbohydrates; // Węglowodany na 100 g

    @ColumnInfo(name = "ingredient_salt")
    public float salt; // Sól na 100 g


    public Ingredient() {

    }

    @Ignore
    public Ingredient(String name, String type, boolean isChecked, float calories, float proteins, float fats, float carbohydrates, float salt) {
        this.name = name;
        this.type = type;
        this.isChecked = isChecked;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.salt = salt;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getSalt() {
        return salt;
    }

    public void setSalt(float salt) {
        this.salt = salt;
    }
}

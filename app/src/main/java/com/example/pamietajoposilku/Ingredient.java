package com.example.pamietajoposilku;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Ingredient implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int _ingredientId;
    @ColumnInfo(name = "ingredient_name")
    private String _ingredientName;
    @ColumnInfo(name = "ingredient_amount")
    private String _ingredientAmount;
    @ColumnInfo(name = "ingredient_unit")
    private String _ingredientUnit;

    @ColumnInfo(name = "_recipeId")
    private int _recipeId;

    public Ingredient(String ingredientName, String ingredientAmount, String ingredientUnit){
        this._ingredientName = ingredientName;
        this._ingredientAmount = ingredientAmount;
        this._ingredientUnit = ingredientUnit;
    }

    public String get_ingredintString(){
        return get_ingredientName() + ";" + get_ingredientAmount() + ";" + get_ingredientUnit() + ";";
    }

    public int get_ingredientId() {
        return _ingredientId;
    }

    public String get_ingredientName() {
        return _ingredientName;
    }

    public String get_ingredientAmount() {
        return _ingredientAmount;
    }

    public String get_ingredientUnit() {
        return _ingredientUnit;
    }

    public void set_ingredientId(int _ingredientId) {
        this._ingredientId = _ingredientId;
    }

    public void set_ingredientName(String _ingredientName) {
        this._ingredientName = _ingredientName;
    }

    public void set_ingredientAmount(String _ingredientAmount) {
        this._ingredientAmount = _ingredientAmount;
    }

    public void set_ingredientUnit(String _ingredientUnit) {
        this._ingredientUnit = _ingredientUnit;
    }

    public int get_recipeId() {
        return _recipeId;
    }

    public void set_recipeId(int _recipeId) {
        this._recipeId = _recipeId;
    }
}

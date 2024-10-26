package com.example.pamietajoposilku;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import java.util.List;

@Entity
public class Recipe implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int _recipeId;
    @ColumnInfo(name = "recipe_name")
    private String _recipeName;
    @ColumnInfo(name = "recipe_ingredients")
    private String _recipeIngredients;
    @ColumnInfo(name = "recipe_preparation")
    private String _recipePreparation;

    public Recipe(String recipeName, String recipeIngredients, String recipePreparation){
        this._recipeName = recipeName;
        this._recipeIngredients = recipeIngredients;
        this._recipePreparation = recipePreparation;
    }

    public int get_recipeId() {
        return _recipeId;
    }

    public String getRecipeName(){
        return this._recipeName;
    }
    public String getRecipeIngredients(){
        return this._recipeIngredients;
     }
    public String getRecipePreparation(){
       return this._recipePreparation;
    }

    public void set_recipeId(int _recipeId) {
        this._recipeId = _recipeId;
    }

    public void set_recipeName(String _recipeName) {
        this._recipeName = _recipeName;
    }

    public void set_recipeIngredients(String _recipeIngredients) {
        this._recipeIngredients = _recipeIngredients;
    }

    public void set_recipePreparation(String _recipePreparation) {
        this._recipePreparation = _recipePreparation;
    }
}

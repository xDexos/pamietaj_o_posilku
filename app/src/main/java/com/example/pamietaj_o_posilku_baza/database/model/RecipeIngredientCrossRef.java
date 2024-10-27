package com.example.pamietaj_o_posilku_baza.database.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "recipe_ingredient_cross_ref",
        primaryKeys = {"recipeId", "ingredientId", "unitId"},
        foreignKeys = {
                @ForeignKey(entity = Recipe.class,
                        parentColumns = "id",
                        childColumns = "recipeId",
                        onDelete = CASCADE),
                @ForeignKey(entity = Ingredient.class,
                        parentColumns = "id",
                        childColumns = "ingredientId",
                        onDelete = CASCADE),
                @ForeignKey(entity = Unit.class,
                        parentColumns = "unitId",
                        childColumns = "unitId",
                        onDelete = CASCADE)
        })
public class RecipeIngredientCrossRef {
    private int recipeId;
    private int ingredientId;
    private int unitId;
    private double quantity;

    // Constructor
    public RecipeIngredientCrossRef(int recipeId, int ingredientId, int unitId, double quantity) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.unitId = unitId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getRecipeId() { return recipeId; }
    public void setRecipeId(int recipeId) { this.recipeId = recipeId; }
    public int getIngredientId() { return ingredientId; }
    public void setIngredientId(int ingredientId) { this.ingredientId = ingredientId; }
    public int getUnitId() { return unitId; }
    public void setUnitId(int unitId) { this.unitId = unitId; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
}
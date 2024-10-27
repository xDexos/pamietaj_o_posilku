package com.example.pamietaj_o_posilku_baza.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class RecipeIngredientWithDetails {
    @Embedded
    public RecipeIngredientCrossRef crossRef;

    @Relation(
            parentColumn = "ingredientId",
            entityColumn = "id"
    )
    public Ingredient ingredient;

    @Relation(
            parentColumn = "unitId",
            entityColumn = "unitId"
    )
    public Unit unit;
}

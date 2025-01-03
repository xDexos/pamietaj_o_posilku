package com.example.application_remake.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.ShoppingList;
import com.example.application_remake.database.table.ShoppingListIngredientCrossRef;

import java.util.List;

public class ShoppingListWithIngredients {

    @Embedded
    public ShoppingList shoppingList;

    @Relation(
            parentColumn = "shopping_list_id",
            entityColumn = "ingredient_id",
            associateBy = @Junction(ShoppingListIngredientCrossRef.class)
    )
    public List<Ingredient> ingredients;
}

package com.example.application_remake.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.ShoppingList;
import com.example.application_remake.database.table.ShoppingListIngredientCrossRef;

import java.util.List;

public class IngredientsWithShoppingLists {

    @Embedded
    public Ingredient ingredient;

    @Relation(
            parentColumn = "ingredient_id",
            entityColumn = "shopping_list_id",
            associateBy = @Junction(ShoppingListIngredientCrossRef.class)
    )
    public List<ShoppingList> shoppingLists;
}

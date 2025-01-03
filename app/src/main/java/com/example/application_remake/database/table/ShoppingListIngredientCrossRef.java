package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

@Entity(primaryKeys = {"shopping_list_id", "ingredient_id"}, tableName = "ShoppingListIngredientCrossRef",
        foreignKeys = {
            @ForeignKey(
                    entity = ShoppingList.class,
                    parentColumns = "shopping_list_id",
                    childColumns = "shopping_list_id",
                    onDelete = ForeignKey.CASCADE // Usunięcie listy zakupów usunie też powiązania
            ),
            @ForeignKey(
                    entity = Ingredient.class,
                    parentColumns = "ingredient_id",
                    childColumns = "ingredient_id",
                    onDelete = ForeignKey.CASCADE // Usunięcie składnika usunie też powiązania
            )
        })
public class ShoppingListIngredientCrossRef {

    @ColumnInfo(name = "shopping_list_id")
    public long shopping_list_id;

    @ColumnInfo(name = "ingredient_id")
    public long ingredient_id;

    public ShoppingListIngredientCrossRef() {

    }

    @Ignore
    public ShoppingListIngredientCrossRef(long shopping_list_id, long ingredient_id) {
        this.shopping_list_id = shopping_list_id;
        this.ingredient_id = ingredient_id;
    }


}

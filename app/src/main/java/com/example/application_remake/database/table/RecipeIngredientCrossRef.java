package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

@Entity(primaryKeys = {"recipe_id", "ingredient_id"}, tableName = "RecipeIngredientCrossRef",
        foreignKeys = {
                @ForeignKey(
                        entity = Recipe.class,
                        parentColumns = "recipe_id",
                        childColumns = "recipe_id",
                        onDelete = ForeignKey.CASCADE // Opcjonalnie: Zdefiniuj zachowanie przy usunięciu rodzica
                ),
                @ForeignKey(
                        entity = Ingredient.class,
                        parentColumns = "ingredient_id",
                        childColumns = "ingredient_id",
                        onDelete = ForeignKey.CASCADE // Opcjonalnie: Zdefiniuj zachowanie przy usunięciu rodzica
                )
        })
public class RecipeIngredientCrossRef {

    @ColumnInfo(name = "recipe_id")
    public long recipe_id;

    @ColumnInfo(name = "ingredient_id")
    public long ingredient_id;

    public RecipeIngredientCrossRef() {

    }

    @Ignore
    public RecipeIngredientCrossRef(long recipe_id, long ingredient_id) {
        this.recipe_id = recipe_id;
        this.ingredient_id = ingredient_id;
    }
}

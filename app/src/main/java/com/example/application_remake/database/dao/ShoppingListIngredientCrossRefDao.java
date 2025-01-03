package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.application_remake.database.IngredientsWithShoppingLists;
import com.example.application_remake.database.ShoppingListWithIngredients;
import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.ShoppingListIngredientCrossRef;

import java.util.List;

@Dao
public interface ShoppingListIngredientCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addShoppingListIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addShoppingListIngredientCrossRefs(List<ShoppingListIngredientCrossRef> shoppingListIngredientCrossRef);

    @Update
    public void updateShoppingListIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef);

    @Delete
    public void deleteShoppingListIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef);

    @Query("DELETE FROM ShoppingListIngredientCrossRef WHERE shopping_list_id = :shoppingListId AND ingredient_id = :ingredientId")
    void deleteByIds(long shoppingListId, long ingredientId);

    @Transaction
    @Query("SELECT * FROM shopping_list")
    public LiveData<List<ShoppingListWithIngredients>> getShoppingListWithIngredients();

    @Transaction
    @Query("SELECT * FROM ingredients")
    public LiveData<List<IngredientsWithShoppingLists>> getIngredientWithShoppingLists();

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE shopping_list_id == :shopping_list_id")
    public LiveData<ShoppingListWithIngredients> getShoppingListWithIngredients(long shopping_list_id);

    @Transaction
    @Query("SELECT * FROM ingredients WHERE ingredient_id == :ingredient_id")
    public LiveData<IngredientsWithShoppingLists> getIngredientWithShoppingLists(long ingredient_id);

    @Transaction
    @Query("SELECT * FROM ingredients " +
            "INNER JOIN ShoppingListIngredientCrossRef ON ingredients.ingredient_id = ShoppingListIngredientCrossRef.ingredient_id " +
            "WHERE ShoppingListIngredientCrossRef.shopping_list_id = :shoppingListId " +
            "AND (ingredients.ingredient_type = :ingredientType OR :ingredientType = 'Wszystkie')")
    public LiveData<List<Ingredient>> getIngredientsForShoppingList(long shoppingListId, String ingredientType);
}

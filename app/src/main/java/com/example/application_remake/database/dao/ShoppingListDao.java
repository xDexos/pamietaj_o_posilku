package com.example.application_remake.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.application_remake.database.table.ShoppingList;

import java.util.List;

@Dao
public interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ShoppingList shoppingList);

    @Update
    void update(ShoppingList shoppingList);

    @Delete
    void delete(ShoppingList shoppingList);

    @Query("SELECT * FROM shopping_list")
    LiveData<List<ShoppingList>> getAllShoppingLists();

    @Query("SELECT * FROM shopping_list WHERE shopping_list_id = :shopping_list_id")
    LiveData<ShoppingList> getShoppingListById(long shopping_list_id);
}

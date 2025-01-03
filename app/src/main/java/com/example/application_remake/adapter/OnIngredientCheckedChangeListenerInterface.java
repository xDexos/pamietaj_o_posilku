package com.example.application_remake.adapter;

import com.example.application_remake.database.table.Ingredient;

public interface OnIngredientCheckedChangeListenerInterface {
    void onIngredientChecked(Ingredient ingredient, boolean isChecked);
}

package com.example.pamietajoposilku;

import java.io.Serializable;

public class Skladnik implements Serializable {

    private String _ingredientName;
    private String _ingredientAmount;
    private String _ingredientUnit;

    public Skladnik(String ingredientName, String ingredientAmount, String ingredientUnit){
        this._ingredientName = ingredientName;
        this._ingredientAmount = ingredientAmount;
        this._ingredientUnit = ingredientUnit;
    }

    public String get_ingredientName() {
        return _ingredientName;
    }

    public String get_ingredientAmount() {
        return _ingredientAmount;
    }

    public String get_ingredientUnit() {
        return _ingredientUnit;
    }
}

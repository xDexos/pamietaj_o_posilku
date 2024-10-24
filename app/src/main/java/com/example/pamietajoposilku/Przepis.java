package com.example.pamietajoposilku;

import java.io.Serializable;
import java.util.ArrayList;

public class Przepis implements Serializable {

    private String _recipeName;
    private ArrayList<Skladnik> _recipeIngredients;
    private String _recipePreparation;

    public Przepis(String recipeName, ArrayList<Skladnik> recipeIngredients, String recipePreparation){
        this._recipeName = recipeName;
        this._recipeIngredients = recipeIngredients;
        this._recipePreparation = recipePreparation;
    }

    public String getRecipeName(){
        return this._recipeName;
    }
    public ArrayList<Skladnik> getRecipeIngredients(){
        return this._recipeIngredients;
     }
    public String getRecipePreparation(){
       return this._recipePreparation;
    }

}

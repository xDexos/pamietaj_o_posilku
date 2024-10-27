package com.example.pamietaj_o_posilku_baza.database;

import com.example.pamietaj_o_posilku_baza.AppDatabase;
import com.example.pamietaj_o_posilku_baza.database.model.Ingredient;
import com.example.pamietaj_o_posilku_baza.database.model.Unit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseInitializer {
    public static void populateDatabase(AppDatabase db) {
        // Dodawanie jednostek
        Unit[] units = {
                new Unit("gramy"),
                new Unit("kilogramy"),
                new Unit("mililitry"),
                new Unit("litry"),
                new Unit("sztuki"),
                new Unit("łyżki"),
                new Unit("łyżeczki"),
                new Unit("szklanki")
        };

        // Dodawanie składników
        Ingredient[] ingredients = {
                new Ingredient("Mąka pszenna"),
                new Ingredient("Cukier"),
                new Ingredient("Sól"),
                new Ingredient("Mleko"),
                new Ingredient("Jajka"),
                new Ingredient("Masło"),
                new Ingredient("Olej"),
                new Ingredient("Drożdże"),
                new Ingredient("Woda"),
                new Ingredient("Ziemniaki"),
                new Ingredient("Cebula"),
                new Ingredient("Czosnek"),
                new Ingredient("Marchewka"),
                new Ingredient("Pieprz")
        };

        // Wykonaj w osobnym wątku
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Dodaj jednostki
            for (Unit unit : units) {
                db.unitDao().insert(unit);
            }

            // Dodaj składniki
            for (Ingredient ingredient : ingredients) {
                db.ingredientDao().insert(ingredient);
            }
        });
        executor.shutdown();
    }
}
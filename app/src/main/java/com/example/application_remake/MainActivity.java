package com.example.application_remake;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.application_remake.database.AppDatabase;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Ingredient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private RecipeRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Włącz tryb pełnoekranowy
        // Ukrywa pasek statusu i pasek nawigacji
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        repository = new RecipeRepository(getApplicationContext());
        database = AppDatabase.getInstance(getApplicationContext());

        // Znajdź NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        // Pobierz NavController z NavHostFragment
        NavController navController = navHostFragment.getNavController();

        // Znajdź BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);

        // Połącz NavController z BottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        {
//            Ingredient ing1 = new Ingredient("Mąka pszenna", "Produkty zbożowe", false , 364f, 10f, 1f, 76f, 0.01f);
//            Ingredient ing2 = new Ingredient("Cukier", "Słodziki", false , 387f, 0f, 0f, 100f, 0f);
//            Ingredient ing3 = new Ingredient("Masło", "Tłuszcze", false , 717f, 0.5f, 81f, 1f, 0.02f);
//            Ingredient ing4 = new Ingredient("Mleko 3,2%", "Nabiał", false , 64f, 3.4f, 3.6f, 4.7f, 0.1f);
//            Ingredient ing5 = new Ingredient("Jajko", "Produkty zwierzęce", false , 143f, 12.6f, 10f, 0.7f, 0.38f);
//            Ingredient ing6 = new Ingredient("Chleb żytni", "Produkty zbożowe", false , 259f, 8.5f, 1.5f, 48f, 1.3f);
//            Ingredient ing7 = new Ingredient("Ser żółty", "Nabiał", false , 402f, 25f, 33f, 1.3f, 2f);
//            Ingredient ing8 = new Ingredient("Ziemniaki", "Warzywa", false , 77f, 2f, 0.1f, 17f, 0.01f);
//            Ingredient ing9 = new Ingredient("Pomidory", "Warzywa", false , 18f, 0.9f, 0.2f, 3.9f, 0.01f);
//            Ingredient ing10 = new Ingredient("Jabłka", "Owoce", false , 52f, 0.3f, 0.2f, 14f, 0.01f);
//            Ingredient ing11 = new Ingredient("Ryż biały", "Produkty zbożowe", false , 130f, 2.4f, 0.3f, 28f, 0.02f);
//            Ingredient ing12 = new Ingredient("Oliwa z oliwek", "Tłuszcze", false , 884f, 0f, 100f, 0f, 0f);
//            Ingredient ing13 = new Ingredient("Pierś z kurczaka", "Mięso", false , 165f, 31f, 3.6f, 0f, 0.07f);
//            Ingredient ing14 = new Ingredient("Łosoś", "Ryby", false , 206f, 22f, 13f, 0f, 0.07f);
//            Ingredient ing15 = new Ingredient("Cebula", "Warzywa", false , 40f, 1.1f, 0.1f, 9.3f, 0.01f);
//            Ingredient ing16 = new Ingredient("Czekolada gorzka 70%", "Słodycze", false , 598f, 7.8f, 42f, 45f, 0.02f);
//            Ingredient ing17 = new Ingredient("Miód", "Słodziki", false , 304f, 0.3f, 0f, 82f, 0.02f);
//            Ingredient ing18 = new Ingredient("Czosnek", "Warzywa", false , 149f, 6.4f, 0.5f, 33f, 0.02f);
//            Ingredient ing19 = new Ingredient("Marchew", "Warzywa", false , 41f, 0.9f, 0.2f, 10f, 0.06f);
//            Ingredient ing20 = new Ingredient("Banany", "Owoce", false , 89f, 1.1f, 0.3f, 23f, 0.01f);
//            Ingredient ing21 = new Ingredient("Mąka żytnia", "Produkty zbożowe", false , 335f, 8.5f, 1.7f, 68f, 0.01f);
//            Ingredient ing22 = new Ingredient("Jogurt naturalny", "Nabiał", false , 59f, 4.3f, 3.3f, 3.6f, 0.05f);
//            Ingredient ing23 = new Ingredient("Śmietana 18%", "Nabiał", false , 191f, 2.4f, 18f, 3.4f, 0.1f);
//            Ingredient ing24 = new Ingredient("Kasza gryczana", "Produkty zbożowe", false , 343f, 13f, 3.1f, 71f, 0.01f);
//            Ingredient ing25 = new Ingredient("Makaron (suchy)", "Produkty zbożowe", false , 371f, 13f, 1.5f, 75f, 0.02f);
//            Ingredient ing26 = new Ingredient("Kapusta biała", "Warzywa", false , 25f, 1.3f, 0.1f, 5.8f, 0.02f);
//            Ingredient ing27 = new Ingredient("Papryka czerwona", "Warzywa", false , 31f, 1f, 0.3f, 6f, 0.01f);
//            Ingredient ing28 = new Ingredient("Ogórek", "Warzywa", false , 12f, 0.6f, 0.1f, 2.2f, 0.01f);
//            Ingredient ing29 = new Ingredient("Buraki", "Warzywa", false , 43f, 1.6f, 0.2f, 10f, 0.06f);
//            Ingredient ing30 = new Ingredient("Mleko kokosowe", "Roślinne", false , 230f, 2.3f, 24f, 3f, 0.03f);
//            Ingredient ing31 = new Ingredient("Twaróg półtłusty", "Nabiał", false , 133f, 17f, 6f, 2.3f, 0.01f);
//            Ingredient ing32 = new Ingredient("Filet z dorsza", "Ryby", false , 82f, 18f, 0.7f, 0f, 0.07f);
//            Ingredient ing33 = new Ingredient("Cukier waniliowy", "Słodziki", false , 394f, 0f, 0f, 98f, 0f);
//            Ingredient ing34 = new Ingredient("Drożdże świeże", "Inne", false , 105f, 8f, 1f, 10.5f, 0.03f);
//            Ingredient ing35 = new Ingredient("Otręby pszenne", "Produkty zbożowe", false , 246f, 17f, 4.2f, 64f, 0.03f);
//            Ingredient ing36 = new Ingredient("Soczewica czerwona", "Rośliny strączkowe", false , 353f, 25f, 1.1f, 60f, 0.02f);
//            Ingredient ing37 = new Ingredient("Ciecierzyca (sucha)", "Rośliny strączkowe", false , 364f, 19f, 6f, 61f, 0.06f);
//            Ingredient ing38 = new Ingredient("Szpinak", "Warzywa", false , 23f, 2.9f, 0.4f, 3.6f, 0.06f);
//            Ingredient ing39 = new Ingredient("Boczek wędzony", "Mięso", false , 541f, 14f, 50f, 1f, 2.8f);
//            Ingredient ing40 = new Ingredient("Sól", "Przyprawy", false , 0f, 0f, 0f, 0f, 100f);
//            Ingredient ing41 = new Ingredient("Pieprz czarny", "Przyprawy", false , 255f, 11f, 3.3f, 64f, 0.02f);
//            Ingredient ing42 = new Ingredient("Majonez", "Sosy", false , 680f, 1.1f, 75f, 1f, 1.5f);
//            Ingredient ing43 = new Ingredient("Ketchup", "Sosy", false , 112f, 1.2f, 0.2f, 25f, 2.3f);
//            Ingredient ing44 = new Ingredient("Musztarda", "Sosy", false , 66f, 4f, 3.6f, 6f, 2.2f);
//            Ingredient ing45 = new Ingredient("Ocet spirytusowy", "Inne", false , 22f, 0f, 0f, 0.2f, 0f);
//            Ingredient ing46 = new Ingredient("Orzechy włoskie", "Orzechy", false , 654f, 15f, 65f, 14f, 0.01f);
//            Ingredient ing47 = new Ingredient("Płatki owsiane", "Produkty zbożowe", false , 379f, 13f, 7f, 69f, 0.02f);
//            Ingredient ing48 = new Ingredient("Migdały", "Orzechy", false , 576f, 21f, 49f, 22f, 0.01f);
//            Ingredient ing49 = new Ingredient("Rodzynki", "Suszone owoce", false , 299f, 3.1f, 0.5f, 79f, 0.02f);
//
//            repository.insertIngredient(ing1);
//            repository.insertIngredient(ing2);
//            repository.insertIngredient(ing3);
//            repository.insertIngredient(ing4);
//            repository.insertIngredient(ing5);
//            repository.insertIngredient(ing6);
//            repository.insertIngredient(ing7);
//            repository.insertIngredient(ing8);
//            repository.insertIngredient(ing9);
//            repository.insertIngredient(ing10);
//            repository.insertIngredient(ing11);
//            repository.insertIngredient(ing12);
//            repository.insertIngredient(ing13);
//            repository.insertIngredient(ing14);
//            repository.insertIngredient(ing15);
//            repository.insertIngredient(ing16);
//            repository.insertIngredient(ing17);
//            repository.insertIngredient(ing18);
//            repository.insertIngredient(ing19);
//            repository.insertIngredient(ing20);
//            repository.insertIngredient(ing21);
//            repository.insertIngredient(ing22);
//            repository.insertIngredient(ing23);
//            repository.insertIngredient(ing24);
//            repository.insertIngredient(ing25);
//            repository.insertIngredient(ing26);
//            repository.insertIngredient(ing27);
//            repository.insertIngredient(ing28);
//            repository.insertIngredient(ing29);
//            repository.insertIngredient(ing30);
//            repository.insertIngredient(ing31);
//            repository.insertIngredient(ing32);
//            repository.insertIngredient(ing33);
//            repository.insertIngredient(ing34);
//            repository.insertIngredient(ing35);
//            repository.insertIngredient(ing36);
//            repository.insertIngredient(ing37);
//            repository.insertIngredient(ing38);
//            repository.insertIngredient(ing39);
//            repository.insertIngredient(ing40);
//            repository.insertIngredient(ing41);
//            repository.insertIngredient(ing42);
//            repository.insertIngredient(ing43);
//            repository.insertIngredient(ing44);
//            repository.insertIngredient(ing45);
//            repository.insertIngredient(ing46);
//            repository.insertIngredient(ing47);
//            repository.insertIngredient(ing48);
//            repository.insertIngredient(ing49);
        }

//        repository.deletePlannedMealById(1);
//        repository.deletePlannedMealById(2);
//        repository.deletePlannedMealById(3);
//        repository.deletePlannedMealById(4);
//        repository.deletePlannedMealById(5);
//        repository.deletePlannedMealById(6);
//        repository.deletePlannedMealById(7);
//        repository.deletePlannedMealById(8);
//        repository.deletePlannedMealById(9);
//        repository.deletePlannedMealById(10);

//        repository.deletePlannedMealById(12);
//        repository.deletePlannedMealById(13);
//        repository.deletePlannedMealById(14);
//        repository.deletePlannedMealById(15);

    }

}
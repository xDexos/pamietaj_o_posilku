package com.example.pamietaj_o_posilku_baza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Insert;

import com.example.pamietaj_o_posilku_baza.database.DatabaseInitializer;

public class MainActivity extends AppCompatActivity {

    private ImageButton goToRecipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        DatabaseInitializer.populateDatabase(db);

        goToRecipesButton = findViewById(R.id.ibRecipesLayout);
        goToRecipesButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
            startActivity(intent);
        });

    }
}
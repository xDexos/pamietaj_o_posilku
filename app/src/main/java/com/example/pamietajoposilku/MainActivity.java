package com.example.pamietajoposilku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        ImageButton przepisyButton = findViewById(R.id.przepisyButton);
        ImageButton planowaniePosilkowButton = findViewById(R.id.planowaniePosilkowButton);

        przepisyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrzepisyActivity.class);
                startActivity(intent);
            }
        });

        planowaniePosilkowButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PlanowaniePosilkowActivity.class);
            startActivity(intent);
        });

    }
}
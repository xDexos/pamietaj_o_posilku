package com.example.pamietajoposilku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PrzepisyActivity extends AppCompatActivity{

    private RecyclerView _PrzepisyRecyclerView;
    private PrzepisyRecycleViewAdapter _PrzepisyRecycleViewAdapter;
    private ArrayList<Przepis> _przepisy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_przepisy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        _PrzepisyRecyclerView = findViewById(R.id.recyclerViewPrzepisy);
        _PrzepisyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        _przepisy = new ArrayList<>();
        _PrzepisyRecycleViewAdapter = new PrzepisyRecycleViewAdapter(_przepisy);
        _PrzepisyRecyclerView.setAdapter(_PrzepisyRecycleViewAdapter);

        Button nowyPrzepisButton = findViewById(R.id.nowyPrzepisButton);

        nowyPrzepisButton.setOnClickListener(view -> {
            Intent intent = new Intent(PrzepisyActivity.this, PrzepisActivity.class);
//            startActivity(intent);
            addRecipeLauncher.launch(intent);
        });

    }

    // Rejestracja ActivityResultLauncher
    private final ActivityResultLauncher<Intent> addRecipeLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Przepis nowyPrzepis = (Przepis) result.getData().getSerializableExtra("NOWY_PRZEPIS");

                    if (nowyPrzepis != null) {
                        _przepisy.add(nowyPrzepis);
                        _PrzepisyRecycleViewAdapter.notifyDataSetChanged();
                    }
                }
            }
    );

}
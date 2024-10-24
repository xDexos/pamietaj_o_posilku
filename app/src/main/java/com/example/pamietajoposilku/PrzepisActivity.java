package com.example.pamietajoposilku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class PrzepisActivity extends AppCompatActivity {

    private SkladnikiRecyclerViewAdapter adapter;
    private ArrayList<Skladnik> skladnikList;

    private EditText przepisNazwa;
    private EditText przepisPrzygotowanie;

    private String inputPrzepisNazwa;
    private String inputPrzepisPrzygotowanie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_przepis);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        skladnikList = new ArrayList<>();
        adapter = new SkladnikiRecyclerViewAdapter(skladnikList);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSkladniki);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button dodajSkladnikButton = findViewById(R.id.dodajSkladnikButton);
        dodajSkladnikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddIngredientDialog();
            }
        });

        przepisNazwa = findViewById(R.id.etPrzepisNazwa);
        przepisPrzygotowanie = findViewById(R.id.etPrzepisPrzygotowanie);

        Button dodajPrzepisButton = findViewById(R.id.dodajPrzepisButton);
        dodajPrzepisButton.setOnClickListener(view -> {

            inputPrzepisNazwa = przepisNazwa.getText().toString();
            inputPrzepisPrzygotowanie = przepisPrzygotowanie.getText().toString();

            dodajPrzepis(view);

        });

    }

    private void showAddIngredientDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.activity_dodaj_skladnik, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        EditText ingredientNameEditText = bottomSheetView.findViewById(R.id.etNazwaSkladnika);
        EditText ingredientAmountEditText = bottomSheetView.findViewById(R.id.etIloscSkladnika);
        EditText ingredientUnitEditText = bottomSheetView.findViewById(R.id.etJednostkaSkladnika);
        Button addButton = bottomSheetView.findViewById(R.id.dodajSkladnikButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ingredientNameEditText != null && ingredientAmountEditText != null && ingredientUnitEditText != null) {
                    String nazwa = ingredientNameEditText.getText().toString();
                    String ilosc = ingredientAmountEditText.getText().toString();
                    String jednostka = ingredientUnitEditText.getText().toString();

                    // Dodanie nowego składnika do listy
                    Skladnik skladnik = new Skladnik(nazwa, ilosc, jednostka);
                    adapter.dodajSkladnik(skladnik);

                    // Zamknięcie dialogu
                    bottomSheetDialog.dismiss();
                }

            }

        });

        bottomSheetDialog.show();
    }

    public void dodajPrzepis(View view){
        String nazwa = przepisNazwa.getText().toString();
        String przygotowanie = przepisPrzygotowanie.getText().toString();

        if (nazwa.isEmpty() || przygotowanie.isEmpty()) {
            Toast.makeText(this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
            return;
        }

        Przepis przepis = new Przepis(inputPrzepisNazwa, skladnikList, inputPrzepisPrzygotowanie);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("NOWY_PRZEPIS", przepis);
        setResult(RESULT_OK, resultIntent);
        finish();

    }

}
package com.example.application_remake.health.bmi_calculator;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application_remake.R;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.BloodPressure;
import com.example.application_remake.database.table.Weight;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BmiCalculatorFragment extends Fragment {

    private EditText bmiWeightEditText;
    private EditText bmiHeightEditText;
    private Button calculateBMIDialogButton;
    private TextView bmiTextView;

    public BmiCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi_calculator, container, false);

        bmiWeightEditText = view.findViewById(R.id.weightEditText);
        bmiHeightEditText = view.findViewById(R.id.heightEditText);
        calculateBMIDialogButton = view.findViewById(R.id.calculateBMIDialogButton);
        bmiTextView = view.findViewById(R.id.bmiTextView);

        calculateBMIDialogButton.setOnClickListener(v -> {
            // Pobierz dane wejściowe (waga i wzrost)
            String weightStr = bmiWeightEditText.getText().toString().trim();
            String heightStr = bmiHeightEditText.getText().toString().trim();

// Sprawdź, czy oba pola są niepuste
            if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
                try {
                    double weight = Double.parseDouble(weightStr); // Waga w kilogramach
                    double height = Double.parseDouble(heightStr); // Wzrost w centymetrach

                    // Konwersja wzrostu na metry
                    height = height / 100.0; // Konwersja centymetrów na metry

                    // Upewnij się, że wzrost jest większy od zera
                    if (height > 0) {
                        // Obliczenie BMI
                        double bmi = weight / (height * height);

                        // Wyświetlanie wyniku
                        bmiTextView.setText("Twoje BMI wynosi: " + String.format("%.2f", bmi));
                    } else {
                        bmiTextView.setText("Wysokość musi być większa od zera.");
                    }
                } catch (NumberFormatException e) {
                    bmiTextView.setText("Wprowadź poprawne dane.");
                }
            }

        });

        return view;
    }
}
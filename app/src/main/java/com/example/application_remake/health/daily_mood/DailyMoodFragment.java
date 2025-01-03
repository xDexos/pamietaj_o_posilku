package com.example.application_remake.health.daily_mood;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.application_remake.R;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.BloodPressure;
import com.example.application_remake.database.table.DailyMood;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DailyMoodFragment extends Fragment {

    private RecipeRepository repository;
    private TextView textView;
    private FloatingActionButton addMoodButton;
    private Dialog dialog;
    private EditText samopoczucieEditText;
    private EditText nastrojEditText;
    private EditText poziomEnergiiEditText;
    private Button addMoodDialogButton;

    public DailyMoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_mood, container, false);

        // Inicjalizacja repozytorium i innych komponentów
        repository = new RecipeRepository(this.requireContext());
        textView = view.findViewById(R.id.moodTextView);
        addMoodButton = view.findViewById(R.id.addMoodButton);
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_daily_mood);
        samopoczucieEditText = dialog.findViewById(R.id.samopoczucieEditText);
        nastrojEditText = dialog.findViewById(R.id.nastrojEditText);
        poziomEnergiiEditText = dialog.findViewById(R.id.poziomEnergiiEditText);
        addMoodDialogButton = dialog.findViewById(R.id.addDailyMoonDialogButton);

        addMoodButton.setOnClickListener(v -> {
            dialog.show();
            samopoczucieEditText.setText("");
            nastrojEditText.setText("");
            poziomEnergiiEditText.setText("");
        });

        addMoodDialogButton.setOnClickListener(v -> {
            String samopoczucie = samopoczucieEditText.getText().toString();
            String nastroj = nastrojEditText.getText().toString();
            String poziomEnergii = poziomEnergiiEditText.getText().toString();
            if (!samopoczucie.isEmpty() && !nastroj.isEmpty() && !poziomEnergii.isEmpty()) {
                DailyMood dailyMood = new DailyMood();
                dailyMood.setSamopoczucie(samopoczucie);
                dailyMood.setNastroj(nastroj);
                dailyMood.setPoziom_energii(poziomEnergii);
                repository.insertDailyMood(dailyMood);
                dialog.dismiss();
            }
        });

        // Pobieranie danych z bazy danych i aktualizacja widoku

        repository.getAllDailyMoods().observe(getViewLifecycleOwner(), dailyMoods -> {
            // Formatowanie tabeli
            StringBuilder tableBuilder = new StringBuilder();

            // Nagłówki tabeli
            tableBuilder.append(String.format("%-2s %-14s %-9s %-16s\n", "Id", "| Samopoczucie", "| Nastrój", "| Poziom energii"));
            tableBuilder.append("---------------------------------\n");

            // Wiersze tabeli
            for (DailyMood dailyMood : dailyMoods) {
                tableBuilder.append(String.format("%-3s %-20s %-20s %-20s\n", dailyMood.getId(), dailyMood.getSamopoczucie(), dailyMood.getNastroj(), dailyMood.getPoziom_energii()));
            }

            // Wyświetlanie tabeli
            System.out.println(tableBuilder.toString());
            textView.setText(tableBuilder.toString());
        });

        return view;
    }
}
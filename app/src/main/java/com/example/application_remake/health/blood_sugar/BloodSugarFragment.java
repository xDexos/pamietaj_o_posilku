package com.example.application_remake.health.blood_sugar;

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

import com.example.application_remake.R;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Weight;
import com.github.mikephil.charting.charts.LineChart;
import com.example.application_remake.database.table.BloodPressure;
import com.example.application_remake.database.table.BloodSugar;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BloodSugarFragment extends Fragment {

    private RecipeRepository repository;

    private TextView bloodSugarTextView;
    private FloatingActionButton addBloodSugarButton;
    private FloatingActionButton openBloodSugarUrlButton;

    private Dialog bloodSugarDialog;

    private EditText bloodSugarDialogEditText;
    private Button addBloodSugarDialogButton;

    private LineChart lineChart;

    public BloodSugarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_sugar, container, false);

        lineChart = view.findViewById(R.id.lineChart);

        repository = new RecipeRepository(this.requireContext());

        bloodSugarTextView = view.findViewById(R.id.bloodSugarTextView);
        addBloodSugarButton = view.findViewById(R.id.addBloodSugarButton);
        openBloodSugarUrlButton = view.findViewById(R.id.openBloodSugarUrlButton);

        bloodSugarDialog = new Dialog(getContext());
        bloodSugarDialog.setContentView(R.layout.dialog_blood_sugar_form);

        bloodSugarDialogEditText = bloodSugarDialog.findViewById(R.id.bloodSugarEditText);
        addBloodSugarDialogButton = bloodSugarDialog.findViewById(R.id.addBloodSugarDialogButton);

        addBloodSugarButton.setOnClickListener(v -> {
            bloodSugarDialog.show();
            bloodSugarDialogEditText.setText("");
        });

        addBloodSugarDialogButton.setOnClickListener(v -> {
            BloodSugar bloodSugar = new BloodSugar();
            String str_blood = bloodSugarDialogEditText.getText().toString();
            bloodSugar.setValue(Float.parseFloat(str_blood));
            if (!str_blood.isEmpty()) {
                repository.insertBloodSugar(bloodSugar);
                bloodSugarDialog.dismiss();
            }
        });

        repository.getAllBloodSugars().observe(getViewLifecycleOwner(), items -> {
            // Formatowanie tabeli
            StringBuilder tableBuilder = new StringBuilder();

            // Nagłówki tabeli
            tableBuilder.append(String.format("%-2s %-15s\n", "Id", "| Poziom cukru"));
            tableBuilder.append("---------------------------------\n");

            // Wiersze tabeli
            for (BloodSugar bloodSugar : items) {
                tableBuilder.append(String.format("%-5s %-5.2f\n", bloodSugar.getId(), bloodSugar.getValue()));
            }

            // Wyświetlanie tabeli
            System.out.println(tableBuilder.toString());
            bloodSugarTextView.setText(tableBuilder.toString());

            updateChart(items);
        });

        openBloodSugarUrlButton.setOnClickListener(v -> {
            // URL strony internetowej
            String url = "https://www.example.com"; // Zmień to na URL, który chcesz otworzyć

            // Tworzenie intencji do otwarcia strony w przeglądarce
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            // Sprawdzenie, czy jest dostępna aplikacja do obsługi intencji (przeglądarka internetowa)
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent); // Uruchom intencję, aby otworzyć stronę
            }
        });

        return view;
    }

    private void updateChart(List<BloodSugar> items) {
        if (items == null || items.isEmpty()) {
            // Jeśli brak danych, wyczyść wykres i zakończ
            lineChart.clear();
            lineChart.invalidate();
            return;
        }

        // Tworzenie listy Entry dla wykresu
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            BloodSugar bloodSugar = items.get(i);
            entries.add(new Entry(i, bloodSugar.getValue()));
        }

        // Tworzenie LineDataSet
        LineDataSet dataSet = new LineDataSet(entries, "Waga");
        dataSet.setColor(R.color.black);
        dataSet.setValueTextSize(10f);
        dataSet.setLineWidth(2f);

        // Konfiguracja osi wykresu
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        // Dodanie danych do wykresu
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        // Aktualizacja wykresu
        lineChart.invalidate();
    }
}
package com.example.application_remake.health.blood_pressure;

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
import com.example.application_remake.database.table.BloodPressure;
import com.example.application_remake.database.table.Weight;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureFragment extends Fragment {

    private RecipeRepository repository;
    private TextView textView;
    private FloatingActionButton addBloodPressureButton;
    private Dialog dialog;
    private EditText bloodPressureEditText1;
    private EditText bloodPressureEditText2;
    private Button addBloodPressureDialogButton;
    private FloatingActionButton openBloodPressureUrlButton;
    private LineChart lineChart;

    public BloodPressureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_pressure, container, false);

        // Inicjalizacja repozytorium i innych komponentów

        lineChart = view.findViewById(R.id.lineChart);

        repository = new RecipeRepository(this.requireContext());

        textView = view.findViewById(R.id.bloodPressureTextView);
        addBloodPressureButton = view.findViewById(R.id.addBloodPressureButton);

        openBloodPressureUrlButton = view.findViewById(R.id.openBloodPressureUrlButton);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_blood_pressure_form);

        bloodPressureEditText1 = dialog.findViewById(R.id.bloodPressure1EditText);
        bloodPressureEditText2 = dialog.findViewById(R.id.bloodPressure2EditText);
        addBloodPressureDialogButton = dialog.findViewById(R.id.addBloodPressureDialogButton);

        addBloodPressureButton.setOnClickListener(v -> {
            dialog.show();
            bloodPressureEditText1.setText("");
            bloodPressureEditText2.setText("");
        });

        addBloodPressureDialogButton.setOnClickListener(v -> {
            BloodPressure bloodPressure = new BloodPressure();
            String str_blood1 = bloodPressureEditText1.getText().toString();
            String str_blood2 = bloodPressureEditText2.getText().toString();
            bloodPressure.setBlood_pressure_value_1(Integer.parseInt(str_blood1));
            bloodPressure.setBlood_pressure_value_2(Integer.parseInt(str_blood2));
            if (!str_blood1.isEmpty() && !str_blood2.isEmpty()) {
                repository.insertBloodPressure(bloodPressure);
                dialog.dismiss();
            }
        });

        repository.getAllBloodPressures().observe(getViewLifecycleOwner(), items -> {
            String text = "";
//            for (BloodPressure bloodPressure : items) {
//                text += bloodPressure.getBlood_pressure_value_1() + "/" + bloodPressure.getBlood_pressure_value_2() + " | ";
//            }
//            textView.setText(text);

            // Formatowanie tabeli
            StringBuilder tableBuilder = new StringBuilder();

            // Nagłówki tabeli
            tableBuilder.append(String.format("%-2s %-15s %-16s\n", "Id", "|Ciśnienie skur", "|Ciśnienie rozkur"));
            tableBuilder.append("---------------------------------\n");

            // Wiersze tabeli
            for (BloodPressure bloodPressure : items) {
                tableBuilder.append(String.format("%-5s %-3d %-1s %-3d\n", bloodPressure.getId(), bloodPressure.getBlood_pressure_value_1(), "/", bloodPressure.getBlood_pressure_value_2()));
            }

            // Wyświetlanie tabeli
            System.out.println(tableBuilder.toString());
            textView.setText(tableBuilder.toString());

            updateChart(items);
        });

        openBloodPressureUrlButton.setOnClickListener(v -> {
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

    private void updateChart(List<BloodPressure> items) {
        if (items == null || items.isEmpty()) {
            lineChart.clear();
            lineChart.invalidate();
            return;
        }

        List<Entry> systolicEntries = new ArrayList<>();
        List<Entry> diastolicEntries = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            BloodPressure bloodPressure = items.get(i);
            systolicEntries.add(new Entry(i, bloodPressure.getBlood_pressure_value_1()));
            diastolicEntries.add(new Entry(i, bloodPressure.getBlood_pressure_value_2()));
        }

        LineDataSet systolicDataSet = new LineDataSet(systolicEntries, "Ciśnienie skurczowe");
        systolicDataSet.setColor(R.color.black);
        systolicDataSet.setLineWidth(2f);

        LineDataSet diastolicDataSet = new LineDataSet(diastolicEntries, "Ciśnienie rozkurczowe");
        diastolicDataSet.setColor(R.color.black);
        diastolicDataSet.setLineWidth(2f);

        LineData lineData = new LineData(systolicDataSet, diastolicDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

}
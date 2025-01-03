package com.example.application_remake.health.weight;

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
import com.example.application_remake.database.table.Weight;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//public class WeightFragment extends Fragment {
//
//    private RecipeRepository repository;
//    private TextView textView;
//    private FloatingActionButton addWeightButton;
//    private Dialog dialog;
//    private EditText weightEditText;
//    private Button addWeightDialogButton;
//
//    public WeightFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_weight, container, false);
//
//        // Inicjalizacja repozytorium i innych komponentów
//
//        repository = new RecipeRepository(this.requireContext());
//
//        textView = view.findViewById(R.id.weightTextView);
//        addWeightButton = view.findViewById(R.id.addWeightButton);
//
//        dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.dialog_weight_form);
//
//        weightEditText = dialog.findViewById(R.id.weightEditText);
//        addWeightDialogButton = dialog.findViewById(R.id.addWeightDialogButton);
//
//        addWeightButton.setOnClickListener(v -> {
//            dialog.show();
//            weightEditText.setText("");
//        });
//
//        addWeightDialogButton.setOnClickListener(v -> {
//            Weight weight = new Weight();
//            String str_weight = weightEditText.getText().toString();
//            weight.setWeight(Float.parseFloat(str_weight));
//            if (!str_weight.isEmpty()) {
//                repository.insertWeight(weight);
//                dialog.dismiss();
//            }
//        });
//
//        repository.getAllWeights().observe(getViewLifecycleOwner(), items -> {
//            String text = "";
////            for (Weight weight : items) {
////                text += weight.getWeight() + " | ";
////            }
////            textView.setText(text);
//
//            // Formatowanie tabeli
//            StringBuilder tableBuilder = new StringBuilder();
//
//            // Nagłówki tabeli
//            tableBuilder.append(String.format("%-2s %-11s\n", "Id", "| Waga (kg)"));
//            tableBuilder.append("---------------------------------\n");
//
//            // Wiersze tabeli
//            for (Weight weight : items) {
//                tableBuilder.append(String.format("%-15s %-10.1f\n", weight.getId(), weight.getWeight()));
//            }
//
//            // Wyświetlanie tabeli
//            System.out.println(tableBuilder.toString());
//            textView.setText(tableBuilder.toString());
//        });
//
//        return view;
//    }
//}

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class WeightFragment extends Fragment {

    private RecipeRepository repository;
    private TextView textView;
    private FloatingActionButton addWeightButton;
    private Dialog dialog;
    private EditText weightEditText;
    private Button addWeightDialogButton;
    private LineChart lineChart;

    public WeightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        // Inicjalizacja repozytorium i innych komponentów
        repository = new RecipeRepository(this.requireContext());

        textView = view.findViewById(R.id.weightTextView);
        addWeightButton = view.findViewById(R.id.addWeightButton);
        lineChart = view.findViewById(R.id.lineChart);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_weight_form);

        weightEditText = dialog.findViewById(R.id.weightEditText);
        addWeightDialogButton = dialog.findViewById(R.id.addWeightDialogButton);

        addWeightButton.setOnClickListener(v -> {
            dialog.show();
            weightEditText.setText("");
        });

        addWeightDialogButton.setOnClickListener(v -> {
            Weight weight = new Weight();
            String str_weight = weightEditText.getText().toString();
            weight.setWeight(Float.parseFloat(str_weight));
            if (!str_weight.isEmpty()) {
                repository.insertWeight(weight);
                dialog.dismiss();
            }
        });

        // Obserwacja danych z bazy
        repository.getAllWeights().observe(getViewLifecycleOwner(), items -> {
            updateTextView(items);
            updateChart(items);
        });

        return view;
    }

    private void updateTextView(List<Weight> items) {
        StringBuilder tableBuilder = new StringBuilder();

        // Nagłówki tabeli
        tableBuilder.append(String.format("%-2s %-11s\n", "Id", "| Waga (kg)"));
        tableBuilder.append("---------------------------------\n");

        // Wiersze tabeli
        for (Weight weight : items) {
            tableBuilder.append(String.format("%-15s %-10.1f\n", weight.getId(), weight.getWeight()));
        }

        // Wyświetlanie tabeli
        textView.setText(tableBuilder.toString());
    }

    private void updateChart(List<Weight> items) {
        if (items == null || items.isEmpty()) {
            // Jeśli brak danych, wyczyść wykres i zakończ
            lineChart.clear();
            lineChart.invalidate();
            return;
        }

        // Tworzenie listy Entry dla wykresu
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Weight weight = items.get(i);
            entries.add(new Entry(i, weight.getWeight()));
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

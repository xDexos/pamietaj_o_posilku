package com.example.application_remake.health.daily_activity;

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
import com.example.application_remake.database.table.DailyActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DailyActivityFragment extends Fragment {

    private RecipeRepository repository;
    private TextView textView;
    private FloatingActionButton addActivityButton;
    private Dialog dialog;
    private EditText activityEditText;
    private Button addActivityDialogButton;
    private FloatingActionButton openActivityUrlButton;

    public DailyActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_activity, container, false);

        // Inicjalizacja repozytorium i innych komponentów

        repository = new RecipeRepository(this.requireContext());

        textView = view.findViewById(R.id.activityTextView);
        addActivityButton = view.findViewById(R.id.addActivityButton);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_daily_activity_form);

        activityEditText = dialog.findViewById(R.id.activityEditText);
        addActivityDialogButton = dialog.findViewById(R.id.addActivityDialogButton);

        openActivityUrlButton = view.findViewById(R.id.openActivityUrlButton);

        addActivityButton.setOnClickListener(v -> {
            dialog.show();
            activityEditText.setText("");
        });

        addActivityDialogButton.setOnClickListener(v -> {
            String activity = activityEditText.getText().toString();

            if (!activity.isEmpty()) {
                DailyActivity dailyActivity = new DailyActivity(activity);
                dailyActivity.setActivity(activity);
                repository.insertDailyActivity(dailyActivity);
                dialog.dismiss();
            }
        });

        // Pobieranie danych z bazy danych i aktualizacja widoku
        repository.getAllDailyActivities().observe(getViewLifecycleOwner(), dailyActivities -> {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (DailyActivity dailyActivity : dailyActivities) {
//                stringBuilder.append(dailyActivity.getActivity()).append(" | ");
//            }

            // Formatowanie tabeli
            StringBuilder tableBuilder = new StringBuilder();

            // Nagłówki tabeli
            tableBuilder.append(String.format("%-2s %-19s\n", "Id", "| Dzienna aktywność"));
            tableBuilder.append("---------------------------------\n");

            // Wiersze tabeli
            for (DailyActivity dailyActivity : dailyActivities) {
                tableBuilder.append(String.format("%-3s %-30s\n", dailyActivity.getId(), dailyActivity.getActivity()));
            }

            // Wyświetlanie tabeli
            System.out.println(tableBuilder.toString());
            textView.setText(tableBuilder.toString());
        });

        openActivityUrlButton.setOnClickListener(v -> {
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
}
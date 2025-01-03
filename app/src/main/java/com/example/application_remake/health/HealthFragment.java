package com.example.application_remake.health;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.application_remake.R;

public class HealthFragment extends Fragment {

    private ImageButton weightImageButton;
    private ImageButton bloodPressureButton;
    private ImageButton bmiCalculatorButton;
    private ImageButton dailyActivityButton;
    private ImageButton bloodSugarButton;
    private ImageButton dailyMoodButton;

    public HealthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);

        weightImageButton = view.findViewById(R.id.weightImageButton);
        weightImageButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_healthFragment_to_weightFragment);
        });

        bloodPressureButton = view.findViewById(R.id.bloodPressureImageButton);
        bloodPressureButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_healthFragment_to_bloodPressureFragment);
        });

        bmiCalculatorButton = view.findViewById(R.id.BMIImageButton);
        bmiCalculatorButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_healthFragment_to_bmiCalculatorFragment);
        });

        dailyActivityButton = view.findViewById(R.id.dailyActivityImageButton);
        dailyActivityButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_healthFragment_to_dailyActivityFragment);
        });

        bloodSugarButton = view.findViewById(R.id.bloodSugarImageButton);
        bloodSugarButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_healthFragment_to_bloodSugarFragment);
        });

        dailyMoodButton = view.findViewById(R.id.dailyMoodImageButton);
        dailyMoodButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_healthFragment_to_dailyMoodFragment);
        });

        return view;
    }
}
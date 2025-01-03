//package com.example.application_remake.calendar;
//
//import android.app.Dialog;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.CalendarView;
//import android.widget.Spinner;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import com.example.application_remake.R;
//import com.example.application_remake.adapter.CalendarRecipeAdapter;
//import com.example.application_remake.adapter.PickRecipeAdapter;
//import com.example.application_remake.adapter.RecycleViewInterface;
//import com.example.application_remake.database.repository.RecipeRepository;
//import com.example.application_remake.database.table.PlannedMeal;
//import com.example.application_remake.database.table.Recipe;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//public class CalendarFragment extends Fragment implements RecycleViewInterface {
//
//    private RecipeRepository repository;
//
//    private CalendarView calendarView;
//    private Calendar calendar;
//
//    private Dialog dialog;
//
//    private RecyclerView recyclerView;
//    private FloatingActionButton addMealFloatingActionButton;
//
//    private PickRecipeAdapter pickAdapter;
//    private CalendarRecipeAdapter calendarRecipeAdapter;
//
//    private TimePicker timePicker;
//    private Spinner mealTypeSpinner;
//
//    private RecyclerView calendarRecipeRecyclerView;
//
//    private List<Recipe> calendarRecipeList;
//    private List<String> spinnerList;
//    private List<PlannedMeal> plannedMealList;
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
//        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
//
//        calendarRecipeList = new ArrayList<>();
//        spinnerList = new ArrayList<>();
//        spinnerList.add("Śniadanie");
//        spinnerList.add("Obiad");
//        spinnerList.add("Kolacja");
//        plannedMealList = new ArrayList<>();
//
//        repository = new RecipeRepository(this.requireContext());
//
//        repository.getAllRecipes().observe(getViewLifecycleOwner(), items -> {
//            pickAdapter.setItems(items);
//        });
//
//        calendarView = view.findViewById(R.id.calendarView);
//        calendar = Calendar.getInstance();
//
//        addMealFloatingActionButton = view.findViewById(R.id.addMealButton);
//        addMealFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.show();
//            }
//        });
//
//        dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.dialog_time_picker_calendar);
//        timePicker = dialog.findViewById(R.id.timePicker);
//        timePicker.setIs24HourView(true);
//
//        mealTypeSpinner = dialog.findViewById(R.id.mealTypeSpinner);
//
//        // Ustaw przezroczyste tło
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        recyclerView = dialog.findViewById(R.id.pickRecipeRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        pickAdapter = new PickRecipeAdapter(getContext(), calendarRecipeList, this);
//        recyclerView.setAdapter(pickAdapter);
//
//        calendarRecipeRecyclerView = view.findViewById(R.id.calendarRecipeRecyclerView);
//        calendarRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        calendarRecipeAdapter = new CalendarRecipeAdapter(getContext(), plannedMealList, this);
//        calendarRecipeRecyclerView.setAdapter(calendarRecipeAdapter);
//
//        long currentDate = calendarView.getDate();
//        repository.getPlannedMealsByDate(currentDate).observe(getViewLifecycleOwner(), items -> {
//            plannedMealList.clear();
//            plannedMealList.addAll(items);
//            calendarRecipeAdapter.notifyDataSetChanged();
//        });
//
//        repository.getPlannedMealsByDate(calendarView.getDate()).observe(getViewLifecycleOwner(), items -> {
//            plannedMealList.clear();
//            plannedMealList.addAll(items);
//            calendarRecipeAdapter.notifyDataSetChanged();
//        });
//
//        getDate();
//        getTime();
//
//        calendarView.setOnDateChangeListener((calendarView, year, month, day) -> {
//            Calendar selectedDate = Calendar.getInstance();
//            selectedDate.set(year, month, day);
//
//            long timestamp = selectedDate.getTimeInMillis();
//
//            repository.getPlannedMealsByDate(timestamp).observe(getViewLifecycleOwner(), items -> {
//                plannedMealList.clear();
//                plannedMealList.addAll(items);
//                calendarRecipeAdapter.notifyDataSetChanged();
//            });
//        });
//
//        timePicker.setOnTimeChangedListener((timePicker, hour, minute) -> {
//            Toast.makeText(getContext(), "Wybrana godzina: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
//        });
//
//        {
//            mealTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                    String item = adapterView.getItemAtPosition(position).toString();
//                    Toast.makeText(getContext(), "Wybrany typ posiłku: " + item, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            mealTypeSpinner.setAdapter(adapter);
//        }
//
//
//        return view;
//    }
//
//    public void getDate(){
//        long date = calendarView.getDate();
//        SimpleDateFormat formatedDate = new SimpleDateFormat("dd/MM/yyyy");
//        String dateString = formatedDate.format(date);
//        Toast.makeText(getContext(), "Wybrana data: " + dateString, Toast.LENGTH_SHORT).show();
//
////        Toast.makeText(getContext(), "Wybrana data: " + date, Toast.LENGTH_SHORT).show();
//    }
//
//    public void getTime(){
//        int hour = timePicker.getHour();
//        int minute = timePicker.getMinute();
//        Toast.makeText(getContext(), "Wybrana godzina: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        if (position >= 0 && position < pickAdapter.recipeList.size()) {
//            // Pobierz element kliknięty w allIngredientsAdapter
//            PlannedMeal plannedMeal = new PlannedMeal();
//            plannedMeal.setRecipeId(pickAdapter.recipeList.get(position).getId());
//            // Pobierz nazwę przepisu z adaptera
//            plannedMeal.setName(pickAdapter.recipeList.get(position).getName());
//            // Pobierz wybraną datę z calendarView
//            plannedMeal.setDate(calendarView.getDate());
//            // Pobierz wybraną godzinę z timePicker
//            plannedMeal.setHour(timePicker.getHour());
//            plannedMeal.setMinute(timePicker.getMinute());
//            Toast.makeText(getContext(), "Wybrana godzina: " + plannedMeal.getHour() + ":" + plannedMeal.getMinute(), Toast.LENGTH_SHORT).show();
//            // Pobierz typ posiłku z spinnera
//            plannedMeal.setType(mealTypeSpinner.getSelectedItemPosition());
//            // Dodaj nowy obiekt PlannedMeal do bazy danych
//            repository.insertPlannedMeal(plannedMeal);
//        }
//        dialog.dismiss();
//
//    }
//
//    @Override
//    public void onEditClick(int position) {
//
//    }
//
//    @Override
//    public void onDeleteClick(int position) {
//
//        if (position >= 0 && position < calendarRecipeAdapter.getItemCount()) {
//            // Pobierz element kliknięty w calendarRecipeAdapter
//            PlannedMeal plannedMeal = calendarRecipeAdapter.mealRecipeList.get(position);
//            // Usuń obiekt PlannedMeal z bazy danych
//            repository.deletePlannedMealById(plannedMeal.getId());
//
//        }
//    }
//}

package com.example.application_remake.calendar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.application_remake.R;
import com.example.application_remake.adapter.CalendarRecipeAdapter;
import com.example.application_remake.adapter.CalendarRecyclerViewInterface;
import com.example.application_remake.adapter.PickRecipeAdapter;
import com.example.application_remake.adapter.RecycleViewInterface;
import com.example.application_remake.addons.AsciiEmojiGenerator;
import com.example.application_remake.database.RecipeWithIngredients;
import com.example.application_remake.database.dao.PlannedMealDao;
import com.example.application_remake.database.dao.RecipeIngredientCrossRefDao;
import com.example.application_remake.database.dao.ShoppingListDao;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.Ingredient;
import com.example.application_remake.database.table.PlannedMeal;
import com.example.application_remake.database.table.Recipe;
import com.example.application_remake.database.table.RecipeIngredientCrossRef;
import com.example.application_remake.database.table.ShoppingList;
import com.example.application_remake.database.table.ShoppingListIngredientCrossRef;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CalendarFragment extends Fragment implements RecycleViewInterface, CalendarRecyclerViewInterface {

    private RecipeRepository repository;
    private ShoppingListDao shoppingListDao;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private CalendarView calendarView;
    private Calendar calendar;

    private Dialog dialog;

    private RecyclerView recyclerView;
    private FloatingActionButton addMealFloatingActionButton;
    private FloatingActionButton createListFloatActionsButton;

    private PickRecipeAdapter pickAdapter;
    private CalendarRecipeAdapter calendarRecipeAdapter;

    private TimePicker timePicker;
    private Spinner mealTypeSpinner;

    private RecyclerView calendarRecipeRecyclerView;

    private List<Recipe> calendarRecipeList;
    private List<String> spinnerList;
    private List<PlannedMeal> plannedMealList;

    private String dateToSave = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Inicjalizacja list i adapterów
        calendarRecipeList = new ArrayList<>();
        spinnerList = new ArrayList<>();
        spinnerList.add("Śniadanie");
        spinnerList.add("Obiad");
        spinnerList.add("Kolacja");
        plannedMealList = new ArrayList<>();

        repository = new RecipeRepository(this.requireContext());

        calendarView = view.findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();

        // for your date format use
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // set a string to format your current date
        dateToSave = sdf.format(calendar.getTime());

        addMealFloatingActionButton = view.findViewById(R.id.addMealButton);
        addMealFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        createListFloatActionsButton = view.findViewById(R.id.makeListButton);
//        createListFloatActionsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                List<Ingredient> skladnikiDoShoppingListIngredientCrossRef = new ArrayList<>();
//                List<PlannedMeal> zaplanowanePolilki = new ArrayList<>();
//                repository.getPlannedMealsByDate(dateToSave).observe(getViewLifecycleOwner(), plannedMeals -> { //gdzies tutaj musi byc problem, inaczej zrobić pobieranie, nie za pomocą observera
//                    zaplanowanePolilki.clear();
//                    zaplanowanePolilki.addAll(plannedMeals);
//                });
//                for (PlannedMeal plannedMeal : zaplanowanePolilki){
//                    List<Ingredient> tempSkladniki = new ArrayList<>();
//                    long idPrzepisu = plannedMeal.recipeId;
//                    repository.getRecipeWithIngredients(idPrzepisu).observe(getViewLifecycleOwner(), ingredients -> {
//                        tempSkladniki.addAll(ingredients.ingredientsEntities);
//                    });
//                    skladnikiDoShoppingListIngredientCrossRef.addAll(tempSkladniki);
//                }
//                ShoppingList shoppingList = new ShoppingList();
//                shoppingList.setName("Lista zakupów z dnia: " + dateToSave);
//                for (Ingredient ingredient: skladnikiDoShoppingListIngredientCrossRef){
//                    ShoppingListIngredientCrossRef shoppingListIngredientCrossRef = new ShoppingListIngredientCrossRef(shoppingList.getId(), ingredient.getId());
//                    repository.addShoppingListIngredientCrossRef(shoppingListIngredientCrossRef);
//                }
//            }
//        });

        createListFloatActionsButton.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    List<PlannedMeal> plannedMeals = repository.getPlannedMealsByDate2(dateToSave);
                    List<Ingredient> ingredientsToSave = new ArrayList<>();

                    for (PlannedMeal plannedMeal : plannedMeals) {
                        long recipe_id = plannedMeal.recipeId;
                        RecipeWithIngredients recipeWithIngredients = repository.getRecipeWithIngredients2(recipe_id);
                        List<Ingredient> wszystkieSkladniki = recipeWithIngredients.ingredientsEntities;
                        for (Ingredient ingredient : wszystkieSkladniki) {
                            if (!ingredient.isChecked()) {
                                ingredientsToSave.add(ingredient);
                            } else {
                                continue;
                            }
                        }
//                        ingredientsToSave.addAll(recipeWithIngredients.ingredientsEntities);
                    }

                    ShoppingList shoppingList = new ShoppingList();
                    String randomEmoji = AsciiEmojiGenerator.getRandomEmoji();
                    shoppingList.setName(randomEmoji);
                    shoppingList.setDate(dateToSave);
                    long shoppingListId = repository.addShoppingList(shoppingList);

                    for (Ingredient ingredient : ingredientsToSave) {
                        ShoppingListIngredientCrossRef ref = new ShoppingListIngredientCrossRef(shoppingListId, ingredient.getId());
                        repository.addShoppingListIngredientCrossRef(ref);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });


        // Ustawienie dialogu dla dodawania posiłków
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_time_picker_calendar);
        timePicker = dialog.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        mealTypeSpinner = dialog.findViewById(R.id.mealTypeSpinner);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        recyclerView = dialog.findViewById(R.id.pickRecipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pickAdapter = new PickRecipeAdapter(getContext(), calendarRecipeList, this);
        recyclerView.setAdapter(pickAdapter);

        repository.getAllRecipes().observe(getViewLifecycleOwner(), items -> {
            pickAdapter.setItems(items);
        });

        calendarRecipeRecyclerView = view.findViewById(R.id.calendarRecipeRecyclerView);
        calendarRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        calendarRecipeAdapter = new CalendarRecipeAdapter(getContext(), plannedMealList, this);
        calendarRecipeRecyclerView.setAdapter(calendarRecipeAdapter);

        // Obserwacja dla bieżącej daty
        loadPlannedMealsForDate(dateToSave);

        // Obsługa zmiany daty
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {

            // Tworzymy obiekt Calendar i ustawiamy datę
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);

            // Formatujemy datę do formatu String (yyyy-MM-dd)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateToSave = dateFormat.format(calendar.getTime());

            // Wyświetlamy datę w TextView
//            Toast.makeText(getContext(), "Wybrana data: " + dateToSave, Toast.LENGTH_SHORT).show();

            // Załaduj dane dla wybranej daty
            loadPlannedMealsForDate(dateToSave);
        });

        timePicker.setOnTimeChangedListener((timePicker, hour, minute) -> {
//            Toast.makeText(getContext(), "Wybrana godzina: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
        });

        setupMealTypeSpinner();

        return view;
    }

    // Metoda do załadowania posiłków dla wybranej daty
    private void loadPlannedMealsForDate(String date) {

        repository.getPlannedMealsByDate(date).observe(getViewLifecycleOwner(), items -> {
            plannedMealList.clear();
            plannedMealList.addAll(items);
            calendarRecipeAdapter.notifyDataSetChanged();
        });
    }

    private void setupMealTypeSpinner() {
        mealTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Wybrany typ posiłku: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealTypeSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        if (position >= 0 && position < pickAdapter.recipeList.size()) {
            PlannedMeal plannedMeal = new PlannedMeal();

            // Reszta kodu pozostaje bez zmian
            plannedMeal.setRecipeId(pickAdapter.recipeList.get(position).getId());
            plannedMeal.setName(pickAdapter.recipeList.get(position).getName());
            plannedMeal.setDate(dateToSave);
            plannedMeal.setHour(timePicker.getHour());
            plannedMeal.setMinute(timePicker.getMinute());
            plannedMeal.setType(mealTypeSpinner.getSelectedItemPosition());

            repository.insertPlannedMeal(plannedMeal);
            dialog.dismiss();
        }
    }

    @Override
    public void onEditClick(int position) {
        // Implementacja funkcji edycji
    }

    @Override
    public void onDeleteClick(int position) {

    }

    @Override
    public void onCalendarItemDeleteClick(int position) {
        if (position >= 0 && position < calendarRecipeAdapter.getItemCount()) {
            PlannedMeal plannedMeal = calendarRecipeAdapter.mealRecipeList.get(position);
            repository.deletePlannedMealById(plannedMeal.getId());
        }
    }
}

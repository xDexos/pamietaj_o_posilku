package com.example.application_remake.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application_remake.R;
import com.example.application_remake.adapter.ListAdapter;
import com.example.application_remake.adapter.RecycleViewInterface;
import com.example.application_remake.database.repository.RecipeRepository;
import com.example.application_remake.database.table.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements RecycleViewInterface {

    private RecipeRepository repository;

    private ListAdapter adapter;

    private RecyclerView listRecyclerView;

    private List<ShoppingList> shoppingList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        repository = new RecipeRepository(getContext());

        shoppingList = new ArrayList<>();

        listRecyclerView = view.findViewById(R.id.listRecyclerView);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Tworzenie i ustawienie adaptera
        adapter = new ListAdapter(getContext(), shoppingList, this);
        listRecyclerView.setAdapter(adapter);

        loadShoppingLists();

        return view;
    }

    // Metoda do załadowania list zakupów
    private void loadShoppingLists() {
        repository.getAllShoppingLists().observe(getViewLifecycleOwner(), shoppingLists -> {
            shoppingList.clear();
            shoppingList.addAll(shoppingLists);
            adapter.setItems(shoppingLists);
        });
    }

    @Override
    public void onItemClick(int position) {
        NavController navController = Navigation.findNavController(requireView());

        Bundle bundle = new Bundle();
        bundle.putLong("listId", adapter.shoppingListList.get(position).getId());

        navController.navigate(R.id.action_listFragment_to_listDetailsFragment, bundle);
    }

    @Override
    public void onEditClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {
        ShoppingList itemToDelete = adapter.shoppingListList.get(position);
        repository.deleteShoppingList(itemToDelete);

        // Usuń element z adaptera i odśwież listę
        adapter.removeItem(position);
    }
}
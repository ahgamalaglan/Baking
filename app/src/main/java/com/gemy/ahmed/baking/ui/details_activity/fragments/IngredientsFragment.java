package com.gemy.ahmed.baking.ui.details_activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.adapters.IngredientAdapter;
import com.gemy.ahmed.baking.models.Ingredient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IngredientsFragment extends Fragment {


    private IngredientAdapter ingrediantAdapter;
    private LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    private List<Ingredient> ingredients;

    public IngredientsFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingrediantAdapter = new IngredientAdapter();
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.rv_ingredients);
        recyclerView.setAdapter(ingrediantAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            ingredients = getArguments().getParcelableArrayList("ingredients");
            ingrediantAdapter.setIngredients(ingredients);
        }
    }
}

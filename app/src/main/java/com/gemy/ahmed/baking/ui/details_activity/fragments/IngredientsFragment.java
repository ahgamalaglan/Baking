package com.gemy.ahmed.baking.ui.details_activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.adapters.IngredientAdapter;
import com.gemy.ahmed.baking.viewmodels.RecipeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class IngredientsFragment extends Fragment {

    private IngredientAdapter ingredientAdapter;
    private LinearLayoutManager linearLayoutManager;

    public IngredientsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredientAdapter = new IngredientAdapter();
        linearLayoutManager = new LinearLayoutManager(getContext());
        RecipeViewModel recipeViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(RecipeViewModel.class);
        recipeViewModel.getSelectedIngredient().observe(getActivity(), ingredients -> {
            if (ingredients != null)
                ingredientAdapter.setIngredients(ingredients);
            getActivity().setTitle("Ingredients");
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_ingredients);
        recyclerView.setAdapter(ingredientAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

}

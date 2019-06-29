package com.gemy.ahmed.baking.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Step;

import java.util.List;


public class RecipeDetailsFragment extends Fragment implements RecipeDetailsAdapter.OnListItemClickListener {


    private TextView ingredients;
    private RecyclerView recyclerView;
    private List<Step> steps;

    LinearLayoutManager linearLayoutManager;
    RecipeDetailsAdapter recipeDetailsAdapter;

    public RecipeDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingredients = view.findViewById(R.id.tv_ingredients);
        ingredients.setOnClickListener(onClick -> {
            Toast.makeText(getContext(), ingredients.getText().toString(), Toast.LENGTH_SHORT).show();
        });

        linearLayoutManager = new LinearLayoutManager(getContext());
        recipeDetailsAdapter = new RecipeDetailsAdapter(this);
        recyclerView = view.findViewById(R.id.rv_steps_recyclerview);
        recyclerView.setAdapter(recipeDetailsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(Step step) {
    }
}

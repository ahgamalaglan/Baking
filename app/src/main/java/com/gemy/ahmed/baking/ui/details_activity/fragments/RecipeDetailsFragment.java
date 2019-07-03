package com.gemy.ahmed.baking.ui.details_activity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.adapters.StepsAdapter;
import com.gemy.ahmed.baking.models.Ingredient;
import com.gemy.ahmed.baking.models.Step;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RecipeDetailsFragment extends Fragment implements StepsAdapter.OnListItemClickListener {


    private static final String TAG = "DetailsActivity";
    private List<Ingredient> ingredients;
    private StepsAdapter recipeDetailsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private OnStepClickListener onStepClickListener;
    private OnIngredientClickListener onIngredientClickListener;

    public RecipeDetailsFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recipeDetailsAdapter = new StepsAdapter(this);

        if (getArguments() != null) {
            if (getArguments().getParcelableArrayList("steps") != null) {
                List<Step> steps = getArguments().getParcelableArrayList("steps");
                recipeDetailsAdapter.setSteps(steps);
            }

        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView ingredientsText = view.findViewById(R.id.tv_ingredients);
        ingredientsText.setOnClickListener(onClick -> {
            onIngredientClickListener.onIngredientsClick();
        });

        RecyclerView recyclerView = view.findViewById(R.id.rv_steps_recyclerview);
        recyclerView.setAdapter(recipeDetailsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClick(Step step) {
        onStepClickListener.onStepClick(step);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeDetailsFragment.OnStepClickListener) {
            onStepClickListener = (OnStepClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        if (context instanceof RecipeDetailsFragment.OnIngredientClickListener) {
            onIngredientClickListener = (OnIngredientClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnIngredientClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onStepClickListener = null;
    }

    public interface OnStepClickListener {
        void onStepClick(Step step);
    }

    public interface OnIngredientClickListener {
        void onIngredientsClick();
    }

}

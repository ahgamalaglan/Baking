package com.gemy.ahmed.baking.ui.details_activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.adapters.StepsPagerAdapter;
import com.gemy.ahmed.baking.models.Step;
import com.gemy.ahmed.baking.viewmodels.RecipeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;


public class StepsFragment extends Fragment {

    private List<Step> steps;
    private Step selectedStep;
    private RecipeViewModel recipeViewModel;

    public StepsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipeViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(RecipeViewModel.class);
        recipeViewModel.getSelectedRecipe().observe(getActivity(), recipe -> {
            getActivity().setTitle(recipe.getName());
            steps = recipe.getSteps();
        });
        recipeViewModel.getSelectedStep().observe(Objects.requireNonNull(getActivity()), step -> selectedStep = step);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_steps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager mViewPager = view.findViewById(R.id.vp_steps_pager);
        mViewPager.setAdapter(new StepsPagerAdapter(getChildFragmentManager(), steps));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Objects.requireNonNull(getActivity()).setTitle("Step : " .concat(String.valueOf(steps.get(position).getId()+1)));
                recipeViewModel.setSelectedStep(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }


}

package com.gemy.ahmed.baking.ui.details_activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Recipe;
import com.gemy.ahmed.baking.ui.details_activity.fragments.IngredientsFragment;
import com.gemy.ahmed.baking.ui.details_activity.fragments.RecipeDetailsFragment;
import com.gemy.ahmed.baking.ui.details_activity.fragments.StepsFragment;
import com.gemy.ahmed.baking.viewmodels.RecipeViewModel;

public class DetailsActivity extends AppCompatActivity implements RecipeDetailsFragment.OnStepClickListener, RecipeDetailsFragment.OnIngredientClickListener {

    private static final String TAG = "DetailsActivity";
    RecipeDetailsFragment recipeDetailsFragment;
    RecipeViewModel recipeViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recipeViewmodel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        if (getIntent() != null) {
            Recipe recipe = getIntent().getParcelableExtra("recipe");
            recipeViewmodel.setSelectedRecipe(recipe);
        }
        recipeDetailsFragment = new RecipeDetailsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.steps_frag, recipeDetailsFragment)
                .commit();
    }

    @Override
    public void onStepClick(int stepId) {
        recipeViewmodel.setSelectedStep(stepId);
        StepsFragment stepsFragment = new StepsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ingredients_frag, stepsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onIngredientsClick() {
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ingredients_frag, ingredientsFragment)
                .addToBackStack(null)
                .commit();
    }
}

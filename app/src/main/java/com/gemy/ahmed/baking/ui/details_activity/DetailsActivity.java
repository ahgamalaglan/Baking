package com.gemy.ahmed.baking.ui.details_activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Recipe;
import com.gemy.ahmed.baking.models.Step;
import com.gemy.ahmed.baking.ui.details_activity.fragments.IngredientsFragment;
import com.gemy.ahmed.baking.ui.details_activity.fragments.RecipeDetailsFragment;
import com.gemy.ahmed.baking.ui.details_activity.fragments.StepFragment;
import com.gemy.ahmed.baking.ui.details_activity.fragments.StepsFragment;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements RecipeDetailsFragment.OnStepClickListener, RecipeDetailsFragment.OnIngredientClickListener {

    private Recipe recipe;
    private static final String TAG = "DetailsActivity";

    RecipeDetailsFragment recipeDetailsFragment;

    IngredientsFragment ingredientsFragment;
    StepsFragment stepsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        if (getIntent() != null) {
            recipe = getIntent().getParcelableExtra("recipe");
            setTitle(recipe.getName());
        }

        recipeDetailsFragment = new RecipeDetailsFragment();


        Bundle mBundle = new Bundle();
        mBundle.putParcelableArrayList("steps", (ArrayList<? extends Parcelable>) recipe.getSteps());
        recipeDetailsFragment.setArguments(mBundle);
        ///   if (getSupportFragmentManager().getFragments().contains(recipeDetailsFragment)) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.steps_frag, recipeDetailsFragment)
                .commit();
//        } else {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.recipe_details_frag, recipeDetailsFragment)
//                    .addToBackStack("details")
//                    .commit();
//        }

    }

    @Override
    public void onStepClick(Step step,int stepId) {
        stepsFragment = new StepsFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("steps", (ArrayList<? extends Parcelable>) recipe.getSteps());
        bundle.putInt("stepIndex",stepId);
        stepsFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ingredients_frag, stepsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onIngredientsClick() {
        Toast.makeText(this, "kansfvljnv", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onViewCreated: ingredients text clicked");

        ingredientsFragment = new IngredientsFragment();

        Bundle ingredientsBundle = new Bundle();
        ingredientsBundle.putParcelableArrayList("ingredients", (ArrayList<? extends Parcelable>) recipe.getIngredients());
        ingredientsFragment.setArguments(ingredientsBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ingredients_frag, ingredientsFragment)
                .addToBackStack(null)
                .commit();

    }
}

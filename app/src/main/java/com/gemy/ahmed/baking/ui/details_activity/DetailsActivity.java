package com.gemy.ahmed.baking.ui.details_activity;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Recipe;
import com.gemy.ahmed.baking.ui.fragments.RecipeDetailsFragment;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private Recipe recipe;

    RecipeDetailsFragment recipeDetailsFragment;

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
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.recipe_details_frag, recipeDetailsFragment)
                .commit();

    }
}

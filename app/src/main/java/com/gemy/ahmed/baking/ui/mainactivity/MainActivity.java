package com.gemy.ahmed.baking.ui.mainactivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.adapters.RecipesAdapter;
import com.gemy.ahmed.baking.models.Recipe;
import com.gemy.ahmed.baking.ui.details_activity.DetailsActivity;
import com.gemy.ahmed.baking.utils.NetworkUtil;
import com.gemy.ahmed.baking.viewmodels.RecipesViewmodel;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.OnRecipeClickListener {
    private static final String TAG = "MainActivity";
    private RecipesAdapter recipesAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.pb_main_progress_bar);
        RecyclerView recyclerView = findViewById(R.id.rv_recipes);

        RecipesViewmodel recipesViewmodel = ViewModelProviders.of(this).get(RecipesViewmodel.class);

        recipesAdapter = new RecipesAdapter(this);
        recyclerView.setAdapter(recipesAdapter);
        if (NetworkUtil.checkNetworkConnection(this)) {
            progressBar.setVisibility(View.VISIBLE);
            if (getOrientation() == 1) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                Log.d(TAG, "onCreate: orientation portrait");

            } else if (getOrientation() == 2) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
                recyclerView.setLayoutManager(gridLayoutManager);
                Log.d(TAG, "onCreate: orientation landscape");
            }
            recyclerView.setHasFixedSize(true);

            recipesViewmodel.getRecipes().observe(this, recipes -> {
                if (recipes != null) {
                    Log.d(TAG, "onCreate: recipes" + recipes.size());
                    progressBar.setVisibility(View.INVISIBLE);
                    recipesAdapter.setRecipes(recipes);
                }
            });


        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Snackbar.make(progressBar,
                    getString(R.string.network_error),
                    Snackbar.LENGTH_LONG).show();
        }
    }

    private int getOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return 1;
        return 2;
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }
}

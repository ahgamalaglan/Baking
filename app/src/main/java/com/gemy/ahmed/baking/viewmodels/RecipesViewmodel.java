package com.gemy.ahmed.baking.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gemy.ahmed.baking.RecipesRepository;
import com.gemy.ahmed.baking.models.Recipe;

import java.util.List;

public class RecipesViewmodel extends AndroidViewModel {

    private LiveData<List<Recipe>> recipes;
    private RecipesRepository recipesRepository;

    public RecipesViewmodel(@NonNull Application application) {
        super(application);
        recipesRepository = new RecipesRepository();
        recipes = recipesRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }

}

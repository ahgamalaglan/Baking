package com.gemy.ahmed.baking.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.baking.RecipesRepository;
import com.gemy.ahmed.baking.models.Recipe;
import com.gemy.ahmed.baking.models.Step;

import java.util.List;

public class RecipesViewModel extends AndroidViewModel {

    private LiveData<List<Recipe>> recipes;

    public RecipesViewModel(@NonNull Application application) {
        super(application);
        RecipesRepository recipesRepository = new RecipesRepository();
        recipes = recipesRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }

}

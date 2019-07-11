package com.gemy.ahmed.baking.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.baking.models.Ingredient;
import com.gemy.ahmed.baking.models.Recipe;
import com.gemy.ahmed.baking.models.Step;

import java.util.List;
import java.util.Objects;

public class RecipeViewModel extends AndroidViewModel {

    private MutableLiveData<Recipe> selectedRecipe;
    private MutableLiveData<Step> selectedStep;
    private MutableLiveData<List<Ingredient>> selectedIngredient;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        selectedRecipe = new MutableLiveData<>();
        selectedStep = new MutableLiveData<>();
        selectedIngredient = new MutableLiveData<>();
    }

    public LiveData<Recipe> getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe recipe) {
        selectedRecipe.postValue(recipe);
        selectedIngredient.postValue(recipe.getIngredients());

    }

    public LiveData<Step> getSelectedStep() {
        return selectedStep;
    }

    public void setSelectedStep(int stepId) {
        selectedStep.postValue(
                Objects.requireNonNull(selectedRecipe.getValue()).getSteps().get(stepId));
    }

    public MutableLiveData<List<Ingredient>> getSelectedIngredient() {
        return selectedIngredient;
    }

}

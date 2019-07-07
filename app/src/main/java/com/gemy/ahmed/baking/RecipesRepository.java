package com.gemy.ahmed.baking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.baking.models.Recipe;
import com.gemy.ahmed.baking.utils.NetworkUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesRepository {
    private static final String TAG = "RecipesRepository";
    private MutableLiveData<List<Recipe>> recipes;

    public RecipesRepository() {
        recipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        NetworkUtil.getRetrofitClient().getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipes.postValue(response.body());
                Log.d(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                recipes.postValue(null);
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
        return recipes;
    }
}

package com.gemy.ahmed.baking.utils;


import com.gemy.ahmed.baking.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}

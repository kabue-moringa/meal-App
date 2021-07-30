package com.moringaschool.mealapp.network;

import com.moringaschool.mealapp.models.Meal;
import com.moringaschool.mealapp.models.MealsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi{
    @GET("/api/json/v1/1/search.php")
    Call<MealsResponse> getMealList(
            @Query("s") String meal
    );


}

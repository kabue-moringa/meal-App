package com.moringaschool.mealapp.network;

import com.moringaschool.mealapp.models.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi{
//    @GET("search.php")
//    Call<MealSearchResponse> searchMeal(
//            @Query("s") String query
//    );
    @GET("lookup.php")
    Call<List<Meal>>getMeal(

    );


}

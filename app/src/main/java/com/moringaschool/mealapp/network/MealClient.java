package com.moringaschool.mealapp.network;

import android.util.Log;

import com.moringaschool.mealapp.network.MealApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringaschool.mealapp.Constants.MEAL_BASE_URL;

public class MealClient {
    private static Retrofit retrofit = null;


        public static MealApi getRetrofitClient () {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(MEAL_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit.create(MealApi.class);
        }
    }

//        if (retrofit == null) {
//
//           retrofit = new Retrofit.Builder()
//                    .baseUrl(MEAL_BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//
//        }
//        Log.e("TAG","retrofit " + retrofit.create(MealApi.class));
//        return retrofit.create(MealApi.class);
//
//    }



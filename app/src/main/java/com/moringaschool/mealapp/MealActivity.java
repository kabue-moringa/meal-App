package com.moringaschool.mealapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.mealapp.adapters.MealListAdapter;
import com.moringaschool.mealapp.models.Meal;
import com.moringaschool.mealapp.network.MealApi;
import com.moringaschool.mealapp.network.MealClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealActivity extends AppCompatActivity {
//    private static final String TAG = MealActivity.class.getSimpleName();
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;

    private MealListAdapter mAdapter;
public String meal;

//    private String[] meals = new String[] {"Chicken", "Beef", "Pizza","Burger","scellaneous", "Pasta", "Pork", "Seafood",
//            "Side", "Starter", "Vegan", "Vegetarian", "Breakfast", "Goat"};
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meal);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    String location = intent.getStringExtra("location");

    MealApi client = MealClient.getClient();

    Call call = client.getMeal();
   call.enqueue(new Callback<Meal>() {
       @Override
       public void onResponse(Call<Meal> call, Response<Meal> response) {

           hideProgressBar();

           if (response.isSuccessful()) {
               meal = response.body().getIdMeal();
               mAdapter = new MealListAdapter(MealActivity.this, meal);
               mRecyclerView.setAdapter(mAdapter);
               RecyclerView.LayoutManager layoutManager =
                       new LinearLayoutManager(MealActivity.this);
               mRecyclerView.setLayoutManager(layoutManager);
               mRecyclerView.setHasFixedSize(true);

               showMeals();
           } else {
               showUnsuccessfulMessage();
           }
       }

       @Override
       public void onFailure(Call<Meal> call, Throwable t) {
           hideProgressBar();
           showFailureMessage();
       }

       private void showMeals() {
           mRecyclerView.setVisibility(View.VISIBLE);
       }
       });

    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    }


package com.moringaschool.mealapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;


    private String[] meals = new String[] {"Chicken", "Beef", "Pizza","Burger","scellaneous", "Pasta", "Pork", "Seafood",
            "Side", "Starter", "Vegan", "Vegetarian", "Breakfast", "Goat"};
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meal);
    ButterKnife.bind(this);
    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, meals);
    mListView.setAdapter(adapter);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String meal = ((TextView)view).getText().toString();
            Toast.makeText(MealActivity.this, meal, Toast.LENGTH_LONG).show();
        }
    });

    Intent intent = getIntent();
    String location = intent.getStringExtra("location");

    mLocationTextView.setText("This are our meals that we offer: " + location);
}
}
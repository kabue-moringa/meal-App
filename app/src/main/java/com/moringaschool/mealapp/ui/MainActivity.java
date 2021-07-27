package com.moringaschool.mealapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
//    private Button mFindMealsButton;
//    private EditText mLocationEditText;
//    binding with butterknife
@BindView(R.id.findMealsButton) Button mFindMealsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView)TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        butterknife
        ButterKnife.bind(this);
        mFindMealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, MealActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String location = mLocationEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, MealActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);


    }
}

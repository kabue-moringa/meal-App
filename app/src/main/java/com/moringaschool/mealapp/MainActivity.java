package com.moringaschool.mealapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
//    private Button mFindMealsButton;
//    private EditText mLocationEditText;
@BindView(R.id.savedMealsButton) Button mSavedMealsButton;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private ValueEventListener mSearchedMealReferenceListener;
private DatabaseReference mSearchedMealReference;

//    binding with butterknife
@BindView(R.id.findMealsButton) Button mFindMealsButton;
    @BindView(R.id.mealEditText) EditText mMealEditText;
    @BindView(R.id.appNameTextView)TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedMealReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_MEAL);

        mSearchedMealReferenceListener = mSearchedMealReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot mealSnapshot : dataSnapshot.getChildren()){
                    String meal = mealSnapshot.getValue().toString();
                    Log.d("Meals updated", "meal: " + meal);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindMealsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == mFindMealsButton) {
            String meal = mMealEditText.getText().toString();

            saveMealToFirebase(meal);

//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }

            Intent intent = new Intent(MainActivity.this, MealListActivity.class);
            intent.putExtra("meal", meal);
            startActivity(intent);
        }
    }


    public void saveMealToFirebase(String meal) {
        mSearchedMealReference.push().setValue(meal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedMealReference.removeEventListener(mSearchedMealReferenceListener);
    }
}
//               super.onCreate(savedInstanceState);
//               setContentView(R.layout.activity_main);
//               ButterKnife.bind(this);
//
//        mFindMealsButton.setOnClickListener(this);
//        mFindMealsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v == mFindMealsButton) {
//                    String meal = mMealEditText.getText().toString();
//
//                    saveMealToFirebase(meal);
//                    Intent intent = new Intent(MainActivity.this, MealListActivity.class);
//                    intent.putExtra("meal", meal);
//                    startActivity(intent);
//                }
//            }
//
//            public void saveMealToFirebase(String meal) {
//                mSearchedMealReference.push().setValue(meal);
//            }
//            });
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        String meal = mMealEditText.getText().toString();
//        Intent intent = new Intent(MainActivity.this, MealListActivity.class);
//        intent.putExtra("meal", meal);
//        startActivity(intent);
//
//
//    }
//}

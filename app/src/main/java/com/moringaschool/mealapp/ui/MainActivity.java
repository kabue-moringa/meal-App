package com.moringaschool.mealapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.mealapp.Constants;
import com.moringaschool.mealapp.MealListActivity;
import com.moringaschool.mealapp.R;

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
//    @BindView(R.id.mealEditText) EditText mMealEditText;
    @BindView(R.id.appNameTextView)TextView mAppNameTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

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

            Intent intent = new Intent(MainActivity.this, MealListActivity.class);
//            intent.putExtra("meal", meal);
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

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
    @Override
    public void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}



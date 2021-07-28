package com.moringaschool.mealapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.mealapp.Constants;
import com.moringaschool.mealapp.R;
import com.moringaschool.mealapp.adapters.FirebasemealViewHolder;
import com.moringaschool.mealapp.models.Meal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedMealList extends AppCompatActivity {
    private DatabaseReference mMealReference;
    private FirebaseRecyclerAdapter<Meal, FirebasemealViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.errorTextView)
    TextView mErrorTextView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        ButterKnife.bind(this);

        mMealReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_MEAL);

    }
}
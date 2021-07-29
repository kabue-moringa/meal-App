package com.moringaschool.mealapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.moringaschool.mealapp.Constants;
import com.moringaschool.mealapp.R;
import com.moringaschool.mealapp.adapters.FirebasemealViewHolder;
import com.moringaschool.mealapp.models.Meal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedMealList extends AppCompatActivity {

    private DatabaseReference mMealReference;
    private FirebaseRecyclerAdapter<Meal, FirebasemealViewHolder> mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

//    @BindView(R.id.errorTextView)
//    TextView mErrorTextView;
//    @BindView(R.id.progressBar)
//    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();

//        mCompanyReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_COMPANY);

//        hideProgressBar();
//        showCompanys();
    }

    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_SEARCHED_MEAL)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);
        mMealReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_MEAL).child(uid);
        FirebaseRecyclerOptions<Meal> options =
                new FirebaseRecyclerOptions.Builder<Meal>()
                        .setQuery(mMealReference, Meal.class)
                        .build();


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}

//    private DatabaseReference mMealReference;
//    private FirebaseRecyclerAdapter<Meal, FirebasemealViewHolder> mFirebaseAdapter;
//
//    @BindView(R.id.recyclerView)
//    RecyclerView mRecyclerView;
//
//    @BindView(R.id.errorTextView)
//    TextView mErrorTextView;
//
//    @BindView(R.id.progressBar)
//    ProgressBar mProgressBar;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_meal);
//        ButterKnife.bind(this);
//
//        mMealReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_MEAL);
//
//    }

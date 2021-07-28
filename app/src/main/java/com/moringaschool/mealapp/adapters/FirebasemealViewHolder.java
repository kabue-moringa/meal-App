package com.moringaschool.mealapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.mealapp.Constants;
import com.moringaschool.mealapp.R;
import com.moringaschool.mealapp.models.Meal;
import com.moringaschool.mealapp.ui.MealDetailActivity;

import java.util.ArrayList;

public class FirebasemealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebasemealViewHolder(@NonNull  View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindMeal(Meal meal){
        ImageView mealImageView = (ImageView) mView.findViewById(R.id.mealImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.mealNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        nameTextView.setText(meal.getIdMeal());
        categoryTextView.setText(meal.getStrCategory());
        ratingTextView.setText("Rating: " + meal.getStrIngredient1() + "/5");
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Meal> meals = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_MEAL);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    meals.add(snapshot.getValue(Meal.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MealDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }

        });
}
}

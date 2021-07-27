package com.moringaschool.mealapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.mealapp.R;
import com.moringaschool.mealapp.models.Meal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {

    private String mMeals;
    private Context mContext;

    public MealListAdapter(Context context, String meals) {
        mContext = context;
        mMeals = meals;
    }


    public class MealViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mealImageView)ImageView mMealImageView;

        @BindView(R.id.mealNameTextView)TextView mNameTextView;

        @BindView(R.id.categoryTextView) TextView mCategoryTextView;

        @BindView(R.id.ratingTextView) TextView mRatingTextView;


        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindMeals(Meal meal){

        }
    }


    @Override
    public void onBindViewHolder(@NonNull MealListAdapter.MealViewHolder holder, int position) {

    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}

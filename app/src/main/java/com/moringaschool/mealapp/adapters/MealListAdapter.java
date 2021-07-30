package com.moringaschool.mealapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.mealapp.R;
import com.moringaschool.mealapp.models.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {

    private List<Meal> mMeals;
    private Context mContext;

    public MealListAdapter(Context context, List<Meal> meals) {
        mContext = context;
        mMeals = meals;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_list_item,parent,false);
       MealViewHolder viewHolder = new MealViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  MealListAdapter.MealViewHolder holder, int position) {
        holder.bindMeals(mMeals.get(position));

    }

    @Override
    public int getItemCount() {
        return mMeals.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.strAreaTextView)  TextView mAreaTextView;


        @BindView(R.id.strInstructionsTextView)  TextView mInstructionTextView;

        @BindView(R.id.strTagsTextView)  TextView mTagsTextView;

        @BindView(R.id.mealTextView)  TextView mMealTextView;

        @BindView(R.id.mealImageView) ImageView mImageView;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
           mContext = itemView.getContext();
        }
public void bindMeals(Meal meal){
    mInstructionTextView.setText(meal.getStrInstructions());
    mTagsTextView.setText(meal.getStrTags());
    mAreaTextView.setText(meal.getStrArea());
    Picasso.get().load((Uri) meal.getStrImageSource()).into(mImageView);

        }
    }
}


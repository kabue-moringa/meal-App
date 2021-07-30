package com.moringaschool.mealapp.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.mealapp.models.Meal;
import com.moringaschool.mealapp.ui.MealDetailFragment;

import java.util.List;

public class MealPagerAdapter extends FragmentPagerAdapter {
    private List<Meal> mMeal;


    public MealPagerAdapter(FragmentManager fm, int behavior, List<Meal> mMeal) {
        super(fm, behavior);
        mMeal = mMeal;
    }

    @Override
    public int getCount() {
        return mMeal.size();
    }

    @NonNull
    @Override
    public MealDetailFragment getItem(int position) {
        return MealDetailFragment.newInstance(mMeal.get(position));
    }
}

//    @Override
//    public boolean isViewFromObject(@NonNull View view,  Object object) {
//        return false;
//    }
//}

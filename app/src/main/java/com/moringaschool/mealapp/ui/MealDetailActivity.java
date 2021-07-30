package com.moringaschool.mealapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.moringaschool.mealapp.R;
import com.moringaschool.mealapp.adapters.MealPagerAdapter;
import com.moringaschool.mealapp.models.Meal;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private MealPagerAdapter adapterViewPager;
    List<Meal> Meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        ButterKnife.bind(this);
        Meal = Parcels.unwrap(getIntent().getParcelableExtra("Product"));
        int startingPosition =getIntent().getIntExtra("position",0);
        adapterViewPager = new MealPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,Meal);
         mViewPager.setAdapter(adapterViewPager);
//        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }

}

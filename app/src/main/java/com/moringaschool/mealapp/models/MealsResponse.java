
package com.moringaschool.mealapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MealsResponse {

    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MealsResponse() {
    }

    /**
     * 
     * @param meals
     */
    public MealsResponse(List<Meal> meals) {
        super();
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}

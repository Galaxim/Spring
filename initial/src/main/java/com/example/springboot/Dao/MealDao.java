package com.example.springboot.Dao;

import com.example.springboot.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public abstract class MealDao implements JpaRepository<Meal, Long> {
    List<Meal> meals = new ArrayList<>();
    public void addMeal(Meal meal){
        this.meals.add(meal);
    }
    public void deleteMeal(String mealName){
        this.meals.removeIf(meal->meal.getName().equals(mealName));
    }
    public void updateMeal(Meal meal){
        this.meals.removeIf(m->m.getName().equals(meal.getName()));
        this.meals.add(meal);
    }

    public List<Meal> getMeals() {
        return this.meals;
    }
}

package com.example.springboot.Service;

import com.example.springboot.Dao.MealDao;
import com.example.springboot.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
    private MealDao mealDao;

    @Autowired
    public MealService(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    public void addMeal(Meal meal){
        if (meal == null) throw new IllegalArgumentException("Meal can't be null!");
        if (meal.getName() == null || meal.getName().isEmpty()) throw new IllegalArgumentException("Meal can't be null or empty!");
        mealDao.addMeal(meal);
    }
    public void deleteMeal(String mealName){
        mealDao.deleteMeal(mealName);
    }
    public void updateMeal(Meal meal){
        mealDao.updateMeal(meal);
    }

    public List<Meal> getMeals() {
        return mealDao.getMeals();
    }
    public void insertMealTest(){
        mealDao.save(new Meal("A",2,"B"));
    }
}

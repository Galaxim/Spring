package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class OtherMealController {
    @GetMapping("/meals")
    public List<Meal> getMeals() {
        List<Meal> meals = new ArrayList<>();

        meals.add(new Meal("pizza", 6.00,"good"));
        meals.add(new Meal("superpizza", 12.50,"very good"));
        meals.add(new Meal("iperpizza", 14.40,"perfect"));

        return meals;
    }
}

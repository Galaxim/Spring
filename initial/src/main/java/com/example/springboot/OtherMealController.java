package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class OtherMealController {
    private List<Meal> meals = new ArrayList<>();

    public OtherMealController() {
        meals.add(new Meal("pizza", 6.00, "good"));
        meals.add(new Meal("superpizza", 12.50, "very good"));
        meals.add(new Meal("iperpizza", 14.40, "perfect"));
    }

    @GetMapping("/meals")
    public List<Meal> getMeals() {
        return meals;
    }

    @GetMapping("/meal/{name}")
    public Meal getMealByName(@PathVariable("name") String name) {
        for (Meal meal : meals) {
            if (meal.getName().equalsIgnoreCase(name)) {
                return meal;
            }
        } throw new MealNotFoundException("Meal not found with name: " + name);
    }@GetMapping("/meal/{name}")
    public Meal getMealByDescription(@PathVariable("description") String description) {
        for (Meal meal : meals) {
            if (meal.getDescription().equalsIgnoreCase(description)) {
                return meal;
            }
        } throw new MealNotFoundException("Meal not found with this description: " + description);
    }
}

package com.example.springboot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MealController {

    List<Meal> meals = new ArrayList<>();

    public MealController() {
        meals.add(new Meal("Pizza", 6.50,"good"));
        meals.add(new Meal("Superpizza", 12.00,"supergood"));
        meals.add(new Meal("Hot Dog", 4.80,"meh"));
    }

    @GetMapping("/meal/price")
    public List<Meal> getMealsByPriceRange(@RequestParam("min") double minPrice, @RequestParam("max") double maxPrice) {
        List<Meal> filteredMeals = new ArrayList<>();

        for (Meal meal : meals) {
            if (meal.getPrice() >= minPrice && meal.getPrice() <= maxPrice) {
                filteredMeals.add(meal);
            }
        }

        return filteredMeals;
    }
}
package com.example.springboot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
public class MealController {

    List<Meal> meals = new ArrayList<>();

    public MealController() {
        meals.add(new Meal("Pizza", 6.50,"good"));
        meals.add(new Meal("Superpizza", 12.00,"supergood"));
        meals.add(new Meal("Hot Dog", 4.80,"meh"));
    }

    @DeleteMapping("/meal/price/{price}")
    public ResponseEntity<String> deleteMealsAbovePrice(@PathVariable double price) {
        Iterator<Meal> iterator = meals.iterator();
        boolean deleted = false;
        while (iterator.hasNext()) {
            Meal meal = iterator.next();
            if (meal.getPrice() > price) {
                iterator.remove();
                deleted = true;
            }
        }
        if (deleted) {
            return ResponseEntity.ok("Meals deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
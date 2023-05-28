package com.example.springboot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/meal")
    public ResponseEntity<String> addMeal(@RequestBody Meal newMeal) {
        meals.add(newMeal);
        return ResponseEntity.ok("Meal added successfully");
    }
}
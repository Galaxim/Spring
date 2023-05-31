package com.example.springboot;
import com.example.springboot.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
public class MealController {

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(value = "/get/meals")
    public ResponseEntity<List<Meal>> getMeals() {
        return ResponseEntity.ok(mealService.getMeals());
    }

    @PutMapping(value = "put/meal")
    public ResponseEntity<String> putMeal(@RequestBody Meal meal) {
        try {
            mealService.addMeal(meal);
            return ResponseEntity.ok("Meal added!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @DeleteMapping(value = "delete/meal/{mealName}")
    public ResponseEntity<String> deleteMeal(@PathVariable String mealName){
        mealService.deleteMeal(mealName);
        return ResponseEntity.ok("Meal deleted!");
    }
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
    @PutMapping("/meal/{name}")
    public ResponseEntity<String> updateMeal(@PathVariable String name, @RequestBody Meal updatedMeal) {
        for (Meal meal : meals) {
            if (meal.getName().equals(name)) {
                meal.setName(updatedMeal.getName());
                meal.setPrice(updatedMeal.getPrice());
                meal.setDescription(updatedMeal.getDescription());
                return ResponseEntity.ok("Meal updated successfully.");
            }
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/meal/{name}")
    public ResponseEntity<String> removeMeal(@PathVariable String name) {
        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            Meal meal = iterator.next();
            if (meal.getName().equals(name)) {
                iterator.remove();
                return ResponseEntity.ok("Meal deleted successfully.");
            }
        }
        return ResponseEntity.notFound().build();
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


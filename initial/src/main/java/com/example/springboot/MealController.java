package com.example.springboot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MealController {

    Meal pizza = new Meal("Pizza", 6.50);
    Meal superpizza = new Meal("Superpizza", 12.00);
    Meal hotDog = new Meal("Hot Dog", 4.80);

    @GetMapping("/meal/{name}")
    public Meal getMealByName(@PathVariable("name") String name) {
        if (name.equalsIgnoreCase("Pizza")) {
            return pizza;
        } else if (name.equalsIgnoreCase("Superpizza")) {
            return superpizza;
        } else if (name.equalsIgnoreCase("Hot Dog")) {
            return hotDog;
        }

        return null;
    }
}


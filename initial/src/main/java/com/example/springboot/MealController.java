package com.example.springboot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MealController {

    Meal pizza = new Meal("Pizza", 6.50,"good");
    Meal superpizza = new Meal("Superpizza", 12.00,"supergood");
    Meal hotDog = new Meal("Hot Dog", 4.80,"meh");

    @GetMapping("/meal/description-match/{phrase}")
    public Meal getMealByDescription(@PathVariable("phrase") String description) {
        if (description.equalsIgnoreCase("good")) {
            return pizza;
        } else if (description.equalsIgnoreCase("supergood")) {
            return superpizza;
        } else if (description.equalsIgnoreCase("meh")) {
            return hotDog;
        }

        return null;
    }
}


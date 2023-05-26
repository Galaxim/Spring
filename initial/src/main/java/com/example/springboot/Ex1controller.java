package com.example.springboot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Ex1controller {
    List<Meal> meals = Arrays.asList(
            new Meal("Pizza", 6.50),
            new Meal("Superpizza", 12.00),
            new Meal("Hot Dog", 4.80)
            );
    @GetMapping(value="/meals")
    public List<Meal> index() {
        return meals;
    }
}

package com.example.springboot.ingredient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private IngredientDao ingredientDao;
    private double MAX_WINTER_TEMP= 20.0;
    @Autowired
    public IngredientService(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
    public List<Ingredient> getAllIngredients() {
        return ingredientDao.findAll();
    }

    public Optional<Ingredient> getIngredientById(Long id) {
        return ingredientDao.findById(id);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientDao.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient updatedIngredient) {
        Optional<Ingredient> existingIngredient = ingredientDao.findById(id);

        if (existingIngredient.isPresent()) {
            Ingredient ingredient = existingIngredient.get();
            ingredient.setName(updatedIngredient.getName());
            ingredient.setIsVegetarian(updatedIngredient.getIsVegetarian());
            ingredient.setIsVegan(updatedIngredient.getIsVegan());
            ingredient.setIsGlutenFree(updatedIngredient.getIsGlutenFree());
            ingredient.setIsLactoseFree(updatedIngredient.getIsLactoseFree());
            return ingredientDao.save(ingredient);
        } else {
            throw new IllegalArgumentException("Ingredient not found with ID: " + id);
        }
    }

    public void deleteIngredient(Long id) {
        Optional<Ingredient> existingIngredient = ingredientDao.findById(id);

        if (existingIngredient.isPresent()) {
            ingredientDao.delete(existingIngredient.get());
        } else {
            throw new IllegalArgumentException("Ingredient not found with ID: " + id);
        }
    }
    public List<Ingredient> getWinterIngredients(){
        double currentTemperatureInCentigrade = getCurrentTemperatureInCentigrade();
        if (currentTemperatureInCentigrade<MAX_WINTER_TEMP) return new ArrayList<>();
        return  ingredientDao.findByIsWinterIngredient(true);
    }
    public Double getCurrentTemperatureInCentigrade() {
        try{
            JSONObject response = Unirest.get("https://api.open-meteo.com/v1/forecast?latitude=43.48&longitude=11.15&current_weather=true")
                    .asJson().getBody().getObject();
            return response.getJSONObject("current_weather").getDouble("temperature");
        } catch (UnirestException e){
            throw new RuntimeException(e);
        }

    }
}

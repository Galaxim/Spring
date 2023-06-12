package com.example.springboot.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private IngredientDao ingredientDao;
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
}

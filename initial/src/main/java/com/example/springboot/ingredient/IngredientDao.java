package com.example.springboot.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientDao extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByIsWinterIngredient(boolean isWinterIngredient);
}

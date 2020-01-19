package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.RecipeIngredient;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
	RecipeIngredient findByName(String name);

}

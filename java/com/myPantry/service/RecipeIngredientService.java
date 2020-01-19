package com.myPantry.service;

import java.util.List;

import com.myPantry.domain.RecipeIngredient;

public interface RecipeIngredientService {
	List<RecipeIngredient> findAll();

	RecipeIngredient findOne(Long id);

	RecipeIngredient findByName(String name);
}

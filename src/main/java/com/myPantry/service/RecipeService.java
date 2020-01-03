package com.myPantry.service;

import java.util.List;

import com.myPantry.domain.Recipe;


public interface RecipeService {
	List<Recipe> findAll();

	Recipe findOne(Long id);
	
}

package com.myPantry.service;

import java.util.List;

import com.myPantry.domain.RecipeIngredientList;

public interface RecipeIngredientListService {
	List<RecipeIngredientList> findAll();

	RecipeIngredientList findOne(Long id);

}

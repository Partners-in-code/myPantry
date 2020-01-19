package com.myPantry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPantry.domain.Recipe;
import com.myPantry.domain.RecipeIngredientList;
import com.myPantry.repository.RecipeIngredientListRepository;
import com.myPantry.service.RecipeIngredientListService;

@Service
public class RecipeIngredientListServiceImpl implements RecipeIngredientListService {
	@Autowired
	private RecipeIngredientListRepository recipeIngredientListRepository;
	@Override
	public List<RecipeIngredientList> findAll() {
		return (List<RecipeIngredientList>) recipeIngredientListRepository.findAll();
	}

	@Override
	public RecipeIngredientList findOne(Long id) {
		return recipeIngredientListRepository.findById(id).orElse(null);
		

	}
}

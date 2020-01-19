package com.myPantry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPantry.domain.Product;
import com.myPantry.domain.RecipeIngredient;
import com.myPantry.domain.RecipeIngredientList;
import com.myPantry.repository.RecipeIngredientRepository;
import com.myPantry.service.RecipeIngredientService;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService{

	@Autowired
	private RecipeIngredientRepository recipeIngredientRepository;
	@Override
	public List<RecipeIngredient> findAll() {
		return (List<RecipeIngredient>) recipeIngredientRepository.findAll();
	}

	@Override
	public RecipeIngredient findOne(Long id) {
		return recipeIngredientRepository.findById(id).orElse(null);
	}

	@Override
	public RecipeIngredient findByName(String name) {
		RecipeIngredient recipeIngredient = new RecipeIngredient();
		recipeIngredient = recipeIngredientRepository.findByName(name);
		return recipeIngredient;
	}

}

package com.myPantry.service.impl;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPantry.domain.Recipe;
import com.myPantry.repository.RecipeRepository;
import com.myPantry.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;

	public List<Recipe> findAll() {
		return (List<Recipe>) recipeRepository.findAll();
	}

	public Recipe findOne(Long id) {
		return recipeRepository.findById(id).orElse(null);
	}

	public List<Recipe> findByName(String name) {
		List<Recipe> recipeList = recipeRepository.findByName(name);

		List<Recipe> activeRecipeList = new ArrayList<>();

		for (Recipe recipe : recipeList) {
			activeRecipeList.add(recipe);
		}

		return activeRecipeList;
	}

	public List<Recipe> blurrySearch(String title) {
		List<Recipe> recipeList = recipeRepository.findByNameContaining(title);
		List<Recipe> activeRecipeList = new ArrayList<>();

		for (Recipe recipe : recipeList) {
			activeRecipeList.add(recipe);
		}

		return activeRecipeList;
	}
}

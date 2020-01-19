package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.RecipeIngredientList;

public interface RecipeIngredientListRepository extends CrudRepository<RecipeIngredientList, Long>{
	RecipeIngredientList findOne(Long id);

}

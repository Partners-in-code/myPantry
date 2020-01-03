package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.Recipe;


public interface RecipeRepository extends CrudRepository<Recipe,Long>{

}

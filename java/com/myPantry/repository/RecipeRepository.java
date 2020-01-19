package com.myPantry.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.Recipe;


public interface RecipeRepository extends CrudRepository<Recipe,Long>{

	List<Recipe> findByName(String name);

	List<Recipe> findByNameContaining(String title);

}

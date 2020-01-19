package com.myPantry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.RecipeIngredientList;

public interface RecipeIngredientListRepository extends CrudRepository<RecipeIngredientList, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM recipe R INNER JOIN RecipeIngredientList RL ON R.id=RL.id")
	RecipeIngredientList findOne(Long id);
	

}

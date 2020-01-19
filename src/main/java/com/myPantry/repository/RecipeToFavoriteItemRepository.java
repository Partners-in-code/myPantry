package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.myPantry.domain.FavoriteItem;
import com.myPantry.domain.RecipeToFavoriteItem;


@Transactional
public interface RecipeToFavoriteItemRepository extends CrudRepository<RecipeToFavoriteItem, Long> {
	void deleteByFavoriteItem(FavoriteItem favoriteItem);
}

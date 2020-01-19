package com.myPantry.service;

import java.util.List;

import com.myPantry.domain.Favorite;
import com.myPantry.domain.FavoriteItem;
import com.myPantry.domain.Recipe;
import com.myPantry.domain.User;

public interface FavoriteItemService {
	List<FavoriteItem> findByFavorite(Favorite favorite);
	
	FavoriteItem updateFavoriteItem(FavoriteItem favoriteItem);
	
	FavoriteItem addRecipeToFavoriteItem(Recipe recipe, User user);
	
	FavoriteItem findById(Long id);
	
	void removeFavoriteItem(FavoriteItem favoriteItem);
	
	FavoriteItem save(FavoriteItem favoriteItem);
	
	//List<FavoriteItem> findByOrder(Order order);
}

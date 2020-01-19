package com.myPantry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPantry.domain.Favorite;
import com.myPantry.domain.FavoriteItem;
import com.myPantry.domain.Recipe;
import com.myPantry.domain.RecipeToFavoriteItem;
import com.myPantry.domain.User;
import com.myPantry.repository.FavoriteItemRepository;
import com.myPantry.repository.RecipeToFavoriteItemRepository;
import com.myPantry.service.FavoriteItemService;


@Service
public class FavoriteItemServiceImpl implements FavoriteItemService{
	
	@Autowired
	private FavoriteItemRepository favoriteItemRepository;
	
	@Autowired
	private RecipeToFavoriteItemRepository recipeToFavoriteItemRepository;
	
	public List<FavoriteItem> findByFavorite(Favorite favorite) {
		return favoriteItemRepository.findByFavorite(favorite);
	}
	
	public FavoriteItem updateFavoriteItem(FavoriteItem favoriteItem) {
		favoriteItemRepository.save(favoriteItem);
		
		return favoriteItem;
	}
	
	public FavoriteItem addRecipeToFavoriteItem(Recipe recipe, User user) {
		List<FavoriteItem> favoriteItemList = findByFavorite(user.getFavorite());
		
		for (FavoriteItem favoriteItem : favoriteItemList) {
			if(recipe.getId() == favoriteItem.getRecipe().getId()) {
				favoriteItemRepository.save(favoriteItem);
				return favoriteItem;
			}
		}
		
		FavoriteItem favoriteItem = new FavoriteItem();
		favoriteItem.setFavorite(user.getFavorite());
		favoriteItem.setRecipe(recipe);
		favoriteItem = favoriteItemRepository.save(favoriteItem);
		
		RecipeToFavoriteItem recipeToFavoriteItem = new RecipeToFavoriteItem();
		recipeToFavoriteItem.setRecipe(recipe);
		recipeToFavoriteItem.setFavoriteItem(favoriteItem);
		recipeToFavoriteItemRepository.save(recipeToFavoriteItem);
		
		return favoriteItem;
	}
	
	public FavoriteItem findById(Long id) {
		return favoriteItemRepository.findById(id).orElse(null);
	}
	
	public void removeFavoriteItem(FavoriteItem favoriteItem) {
		recipeToFavoriteItemRepository.deleteByFavoriteItem(favoriteItem);
		favoriteItemRepository.delete(favoriteItem);
	}
	
	public FavoriteItem save(FavoriteItem favoriteItem) {
		return favoriteItemRepository.save(favoriteItem);
	}
}

package com.myPantry.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myPantry.domain.Favorite;
import com.myPantry.domain.FavoriteItem;
import com.myPantry.domain.Recipe;
import com.myPantry.domain.User;
import com.myPantry.service.FavoriteItemService;
import com.myPantry.service.FavoriteService;
import com.myPantry.service.RecipeService;
import com.myPantry.service.UserService;


@Controller
@RequestMapping("/favorite")
public class FavoriteController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FavoriteItemService favoriteItemService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@RequestMapping("/cart")
	public String favorite(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		Favorite favorite = user.getFavorite();
		
		List<FavoriteItem> favoriteItemList = favoriteItemService.findByFavorite(favorite);
		
		favoriteService.updateFavorite(favorite);
		
		model.addAttribute("favoriteItemList", favoriteItemList);
		model.addAttribute("favorite", favorite);
		
		return "favorite";
	}

	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("recipe") Recipe recipe,
			Model model, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		recipe = recipeService.findOne(recipe.getId());
		
		FavoriteItem favoriteItem = favoriteItemService.addRecipeToFavoriteItem(recipe, user);
		model.addAttribute("addRecipeSuccess", true);
		
		return "forward:/recipeDetail?id="+recipe.getId();
	}
	
	@RequestMapping("/updateFavoriteItem")
	public String updateFavorite(
			@ModelAttribute("id") Long favoriteItemId) {
		FavoriteItem favoriteItem = favoriteItemService.findById(favoriteItemId);
		favoriteItemService.updateFavoriteItem(favoriteItem);
		
		return "forward:/favorite/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		favoriteItemService.removeFavoriteItem(favoriteItemService.findById(id));
		
		return "forward:/favorite/cart";
	}
}

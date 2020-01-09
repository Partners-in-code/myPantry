package com.myPantry.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myPantry.domain.Recipe;
import com.myPantry.domain.User;
import com.myPantry.service.RecipeService;
import com.myPantry.service.UserService;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RecipeService recipeService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Recipe> recipeList = recipeService.findByName(category);
		
		if (recipeList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "recipeshelf";
		}
		
		model.addAttribute("recipeList", recipeList);
		
		return "recipeshelf";
	}
}

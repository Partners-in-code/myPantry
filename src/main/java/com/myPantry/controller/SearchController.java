package com.myPantry.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping("/searchByName")
	public String searchByName(@RequestParam("name") String name, Model model, Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

		String classActiveName = "active" + name;
		classActiveName = classActiveName.replaceAll("\\s+", "");
		classActiveName = classActiveName.replaceAll("&", "");
		model.addAttribute(classActiveName, true);

		List<Recipe> recipeList = recipeService.findByName(name);

		if (recipeList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "recipeshelf";
		}

		model.addAttribute("recipeList", recipeList);

		return "recipeshelf";
	}

	@RequestMapping("/searchRecipe")
	public String searchRecipe(@ModelAttribute("keyword") String keyword, Principal principal, Model model) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

		List<Recipe> recipeList = recipeService.blurrySearch(keyword);

		if (recipeList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "recipeshelf";
		}

		model.addAttribute("recipeList", recipeList);

		return "recipeshelf";
	}
}

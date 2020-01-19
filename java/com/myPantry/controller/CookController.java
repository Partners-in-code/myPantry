package com.myPantry.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myPantry.domain.Product;
import com.myPantry.domain.Recipe;
import com.myPantry.domain.RecipeIngredient;
import com.myPantry.domain.RecipeIngredientList;
import com.myPantry.domain.User;
import com.myPantry.service.ProductService;
import com.myPantry.service.RecipeIngredientListService;
import com.myPantry.service.RecipeIngredientService;
import com.myPantry.service.RecipeService;
import com.myPantry.service.UserService;

@Controller
@RequestMapping("/cook")
public class CookController {
	@Autowired
	private UserService userService;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private ProductService productService;

	@Autowired
	private RecipeIngredientService recipeIngredientService;

	@Autowired
	private RecipeIngredientListService recipeIngredientListService;

	@RequestMapping(value = "/sufficientProducts", method = RequestMethod.GET)
	public String sufficientProducts(Model model, Recipe recipe, Principal principal, HttpServletRequest request)
			throws Exception {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		if (user == null) {
			throw new Exception("User not found");
		}
		RecipeIngredientList recipeIngredientList = recipeIngredientListService.findOne(recipe.getId());
		for (int i = 0; i < recipeIngredientList.getListLenght(); i++) {
			RecipeIngredient recipeIngredient = recipeIngredientList.elementAt(i);
			if (recipeIngredient.getName().contentEquals("apa"))
				continue;

			Product product = productService.findByName(user.getId(), recipeIngredientList.elementAt(i).getName());
			if (product == null)
				return "redirect:/productInsufficientOrInexistent";
			else {
				Integer quant=0;
				if (product.getUnit().equals("ml") && recipeIngredient.getUnit().equals("l")
						|| product.getUnit().equals("g") && recipeIngredient.getUnit().equals("kg")) {
					try {
						quant = -(recipeIngredient.getType()) * 1000
								+ Integer.parseInt(product.getType());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					if (product.getUnit().equals("l") && recipeIngredient.getUnit().equals("ml")
							|| product.getUnit().equals("kg") && recipeIngredient.getUnit().equals("g")) {
						try {
							quant = Integer.parseInt(product.getType()) * 1000
									- (recipeIngredient.getType());

						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						try {
							quant = Integer.parseInt(product.getType())
									- (recipeIngredient.getType());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if(quant<0)
					return "redirect:/productInsufficientOrInexistent";
			}
		}
		return "canCook";
	}

	@RequestMapping(value = "/canCook", method = RequestMethod.POST)
	public String canCook(Model model, Recipe recipe, Principal principal, HttpServletRequest request)
			throws Exception {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		if (user == null) {
			throw new Exception("User not found");
		}
		RecipeIngredientList recipeIngredientList = recipeIngredientListService.findOne(recipe.getId());
		for (int i = 0; i < recipeIngredientList.getListLenght(); i++) {
			RecipeIngredient recipeIngredient = recipeIngredientList.elementAt(i);
			if (recipeIngredient.getName().contentEquals("apa"))
				continue;

			Product product = productService.findByName(user.getId(), recipeIngredientList.elementAt(i).getName());
			Integer quant=0;
			if (product.getUnit().equals("ml") && recipeIngredient.getUnit().equals("l")
						|| product.getUnit().equals("g") && recipeIngredient.getUnit().equals("kg") 
						|| product.getUnit().equals("ml") && recipeIngredient.getUnit().equals("kg")
						|| product.getUnit().equals("g") && recipeIngredient.getUnit().equals("l")) {
					try {
						quant = -(recipeIngredient.getType()) * 1000
								+ Integer.parseInt(product.getType());
						product.setType(quant.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					if (product.getUnit().equals("l") && recipeIngredient.getUnit().equals("ml")
							|| product.getUnit().equals("kg") && recipeIngredient.getUnit().equals("g") 
							|| product.getUnit().equals("l") && recipeIngredient.getUnit().equals("mg")
							|| product.getUnit().equals("kg") && recipeIngredient.getUnit().equals("l")) {
						try {
							quant = Integer.parseInt(product.getType()) * 1000
									- (recipeIngredient.getType());
							product.setType(quant.toString());
							product.setUnit(recipeIngredient.getUnit());
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						try {
							quant = Integer.parseInt(product.getType())
									- (recipeIngredient.getType());
							product.setType(quant.toString());
							product.setUnit(recipeIngredient.getUnit());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		return "cooked";
	}

}

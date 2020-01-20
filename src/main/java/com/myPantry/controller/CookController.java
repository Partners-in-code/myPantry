package com.myPantry.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myPantry.domain.Product;
import com.myPantry.domain.Recipe;
import com.myPantry.domain.User;
import com.myPantry.service.ProductService;
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

	@RequestMapping(value = "/sufficientProducts", produces = "application/json", method = { RequestMethod.GET,
			RequestMethod.PUT })
	public String sufficientProducts(Model model, @PathParam("id") Long id, Principal principal,
			HttpServletRequest request) throws Exception {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		if (user == null) {
			throw new Exception("User not found");
		}

		Recipe recipe = recipeService.findOne(id);

		String ingredients = recipe.getIngrediente();
		String ingredientList[] = ingredients.split(";");

		for (int i = 0; i < ingredientList.length; i++)
			if (!ingredientList[i].isEmpty()) {
				System.out.println("for i=" + i + " " + ingredientList[i]);
				String recIngredient[] = ingredientList[i].split(" ", 3);
				String type = recIngredient[0];
				String unit = recIngredient[1];
				String name = recIngredient[2];
				System.out.println(name + "+" + unit + "+" + type);
				if (name.equals("apa"))
					continue;

				Product userProduct = productService.findByName(user.getId(), name);
				System.out.println("name:" + name);
				if (userProduct == null) {
					model.addAttribute("productName", name);
					model.addAttribute("userProductNull", true);
					return "forward:/recipeDetail?id=" + recipe.getId();
				} else {
					Integer quant = 0;
					if (userProduct.getUnit().equals("ml") && unit.equals("l")
							|| userProduct.getUnit().equals("g") && unit.equals("kg")
							|| userProduct.getUnit().equals("ml") && unit.equals("kg")
							|| userProduct.getUnit().equals("g") && unit.equals("l")) {
						try {
							quant = -Integer.parseInt(type) * 1000 + Integer.parseInt(userProduct.getType());
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						if (userProduct.getUnit().equals("l") && unit.equals("ml")
								|| userProduct.getUnit().equals("kg") && unit.equals("g")
								|| userProduct.getUnit().equals("l") && unit.equals("mg")
								|| userProduct.getUnit().equals("kg") && unit.equals("l")) {
							try {
								quant = Integer.parseInt(userProduct.getType()) * 1000 - Integer.parseInt(type);

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								quant = Integer.parseInt(userProduct.getType()) - Integer.parseInt(type);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					if (quant < 0) {
						model.addAttribute("productName", name);
						model.addAttribute("cantitateInsuficienta", true);
						return "forward:/recipeDetail?id=" + recipe.getId();
					}
				}
			}
		return "redirect:/cook/canCook?id=" + id;
	}

	@RequestMapping(value = "/canCook", produces = "application/json", method = { RequestMethod.GET,
			RequestMethod.PUT })
	public String canCook(Model model, @PathParam("id") Long id, Principal principal, HttpServletRequest request)
			throws Exception {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		if (user == null) {
			throw new Exception("User not found");
		}
		Recipe recipe = recipeService.findOne(id);

		String ingredients = recipe.getIngrediente();
		String ingredientList[] = ingredients.split(";");

		for (int i = 0; i < ingredientList.length; i++) {
			String recIngredient[] = ingredientList[i].split(" ", 3);
			String type = recIngredient[0];
			String unit = recIngredient[1];
			String name = recIngredient[2];

			if (name.equals("apa"))
				continue;

			Product userProduct = productService.findByName(user.getId(), name);

			if (userProduct == null)
				return "redirect:/";
			else {
				Integer quant = 0;

				if (userProduct.getUnit().equals("ml") && unit.equals("l")
						|| userProduct.getUnit().equals("g") && unit.equals("kg")
						|| userProduct.getUnit().equals("ml") && unit.equals("kg")
						|| userProduct.getUnit().equals("g") && unit.equals("l")) {
					try {
						quant = -Integer.parseInt(type) * 1000 + Integer.parseInt(userProduct.getType());
						userProduct.setType(quant.toString());
						productService.save(userProduct);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					if (userProduct.getUnit().equals("l") && unit.equals("ml")
							|| userProduct.getUnit().equals("kg") && unit.equals("g")
							|| userProduct.getUnit().equals("l") && unit.equals("mg")
							|| userProduct.getUnit().equals("kg") && unit.equals("l")) {
						try {
							quant = Integer.parseInt(userProduct.getType()) * 1000 - Integer.parseInt(type);
							userProduct.setType(quant.toString());
							userProduct.setUnit(unit);
							productService.save(userProduct);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						try {
							quant = Integer.parseInt(userProduct.getType()) - Integer.parseInt(type);
							userProduct.setType(quant.toString());
							userProduct.setUnit(unit);
							productService.save(userProduct);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		}
		model.addAttribute("cookRecipeSuccess", true);
		return "forward:/recipeDetail?id=" + recipe.getId();
	}

}
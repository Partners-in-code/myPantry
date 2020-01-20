package com.myPantry.controller;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import com.myPantry.domain.Product;
import com.myPantry.domain.User;
import com.myPantry.service.ProductService;
import com.myPantry.service.UserService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProductPost(Model model, @ModelAttribute("product") Product product, Principal principal,
			HttpServletRequest request) throws Exception {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		if (user == null) {
			throw new Exception("User not found");
		}
		if (productService.productExist(product) == true) {
			Product oldProduct = productService.findByName(user.getId(), product.getName());
			oldProduct.setName(product.getName());
			if (oldProduct.getUnit().equals("ml") && product.getUnit().equals("l")
					|| oldProduct.getUnit().equals("g") && product.getUnit().equals("kg")) {
				try {
					Integer quant = Integer.parseInt(product.getType()) * 1000 + Integer.parseInt(oldProduct.getType());
					oldProduct.setType(quant.toString());
					oldProduct.setUnit(oldProduct.getUnit());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				if (oldProduct.getUnit().equals("l") && product.getUnit().equals("ml")
						|| oldProduct.getUnit().equals("kg") && product.getUnit().equals("g")) {
					try {
						Integer quant = Integer.parseInt(oldProduct.getType()) * 1000
								+ Integer.parseInt(product.getType());
						oldProduct.setType(quant.toString());
						oldProduct.setUnit(product.getUnit());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						Integer a = Integer.parseInt(oldProduct.getType()) + Integer.parseInt(product.getType());
						oldProduct.setType(a.toString());
						oldProduct.setUnit(product.getUnit());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			productService.save(oldProduct);
			MultipartFile productImage = oldProduct.getProductImage();

		} else {
			product.setUser(user);
			productService.save(product);

			MultipartFile productImage = product.getProductImage();
			if (!productImage.isEmpty()) {
				try {
					byte[] bytes = productImage.getBytes();
					String name = product.getId() + ".png";
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File("src/main/resources/static/image/product/" + name)));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/product/productshelf";

	}

	@RequestMapping("/productInfo")
	public String productInfo(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		model.addAttribute("product", product);

		return "productInfo";
	}

	@RequestMapping("/updateProduct")
	public String updateProduct(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		model.addAttribute("product", product);

		return "updateProduct";
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProductPost(@ModelAttribute("product") Product product, Principal principal,
			HttpServletRequest request) {

		User user = userService.findByUsername(principal.getName());
		product.setUser(user);
		productService.save(product);

		MultipartFile productImage = product.getProductImage();

		if (!productImage.isEmpty()) {
			try {
				byte[] bytes = productImage.getBytes();
				String name = product.getId() + ".png";

				Files.delete(Paths.get("src/main/resources/static/image/product/" + name));

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/product/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "redirect:/product/productList";
	}

	@RequestMapping("/productList")
	public String productList(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		List<Product> productList = productService.findAll(user.getId());
		model.addAttribute("productList", productList);

		return "productList";

	}

	@RequestMapping("/productshelf")
	public String productshelf(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		List<Product> productList = productService.findAll(user.getId());
		for (int i = 0; i < productList.size(); i++) {
			try {
				if (Integer.parseInt(productList.get(i).getType()) == 0) {
					Product product = productList.get(i);
					String s = "oneProduct-" + product.getId().toString();
					System.out.println(s);
					productService.removeOne(Long.parseLong(s.substring(11)));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productList = productService.findAll(user.getId());
		model.addAttribute("productList", productList);
		return "productshelf";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("id") String id, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		productService.removeOne(Long.parseLong(id.substring(11)));
		List<Product> productList = productService.findAll(user.getId());
		model.addAttribute("productList", productList);

		return "redirect:/product/productList";
	}

}

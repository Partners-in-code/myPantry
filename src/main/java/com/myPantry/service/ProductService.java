package com.myPantry.service;

import java.util.List;

import com.myPantry.domain.Product;

public interface ProductService {
	Product save(Product product);
	List<Product> findAll();
	
}

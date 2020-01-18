package com.myPantry.service;

import java.util.List;

import com.myPantry.domain.Product;

public interface ProductService {
	Product save(Product product);

	List<Product> findAll(Long id);

	Product findOne(Long id);

	void removeOne(Long id);

	boolean productExist(Product product);

	Product findByName(Long id, String name);

}

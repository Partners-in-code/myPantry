package com.myPantry.service;

import java.util.List;

import com.myPantry.domain.Product;

public interface ProductService {
	public Product save(Product rose) ;
	
	public List<Product> findAll();
	
	public Product findOne(Long id) ;
	
	public void removeOne(Long id);
}

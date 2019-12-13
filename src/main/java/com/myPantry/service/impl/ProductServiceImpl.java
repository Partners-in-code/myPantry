package com.myPantry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPantry.domain.Product;
import com.myPantry.repository.ProductRepository;
import com.myPantry.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	public List<Product> findAll(){
		return (List<Product>) productRepository.findAll();
	}
}

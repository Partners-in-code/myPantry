package com.myPantry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPantry.domain.Product;
import com.myPantry.repository.ProductRepository;
import com.myPantry.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public List<Product> findAll(Long id) {
		return (List<Product>) productRepository.getProductAllDetails(id);
	}

	public Product findOne(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public void removeOne(Long id) {
		productRepository.deleteById(id);

	}

	public boolean productExist(Product product) {
		return productRepository.existsByName(product.getName());
	}

	public Product findByName(Long id, String name) {
		Product product = new Product();
		product = productRepository.findByName(id, name);
		return product;
	}
}

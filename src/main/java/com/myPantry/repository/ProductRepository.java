package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{
	
}

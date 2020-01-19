package com.myPantry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myPantry.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM user U INNER JOIN Product P ON U.id_user=P.id_user AND U.id_user=?")
	public List<Product> getProductAllDetails(Long id);

	public boolean existsByName(String name);

	@Query(nativeQuery = true, value = "SELECT * FROM user U INNER JOIN Product P ON U.id_user=P.id_user AND U.id_user=? AND P.name=?")
	public Product findByName(Long id, String name);
}

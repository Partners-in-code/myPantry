package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	User findByEmail(String email);
}

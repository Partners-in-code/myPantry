package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}

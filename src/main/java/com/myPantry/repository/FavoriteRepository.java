package com.myPantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.myPantry.domain.Favorite;


public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

}

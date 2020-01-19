package com.myPantry.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.myPantry.domain.Favorite;
import com.myPantry.domain.FavoriteItem;


@Transactional
public interface FavoriteItemRepository extends CrudRepository<FavoriteItem, Long>{
	List<FavoriteItem> findByFavorite(Favorite favorite);
}

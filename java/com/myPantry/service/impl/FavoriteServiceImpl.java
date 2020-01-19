package com.myPantry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPantry.domain.Favorite;
import com.myPantry.domain.FavoriteItem;
import com.myPantry.repository.FavoriteRepository;
import com.myPantry.service.FavoriteItemService;
import com.myPantry.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private FavoriteItemService favoriteItemService;

	@Autowired
	private FavoriteRepository favoriteRepository;

	public Favorite updateFavorite(Favorite favorite) {

		List<FavoriteItem> favoriteItemList = favoriteItemService.findByFavorite(favorite);

		for (FavoriteItem favoriteItem : favoriteItemList) {
			favoriteItemService.updateFavoriteItem(favoriteItem);
		}

		favoriteRepository.save(favorite);

		return favorite;
	}

	public void clearFavorite(Favorite favorite) {
		List<FavoriteItem> favoriteItemList = favoriteItemService.findByFavorite(favorite);

		for (FavoriteItem favoriteItem : favoriteItemList) {
			favoriteItem.setFavorite(null);
			favoriteItemService.save(favoriteItem);
		}

		favoriteRepository.save(favorite);
	}

}

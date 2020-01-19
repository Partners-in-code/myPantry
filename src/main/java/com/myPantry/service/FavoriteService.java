package com.myPantry.service;

import com.myPantry.domain.Favorite;

public interface FavoriteService {
	Favorite updateFavorite(Favorite favorite);

	void clearFavorite(Favorite favorite);
}

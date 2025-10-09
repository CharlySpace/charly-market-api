package com.charly.market.favorite.service;

import com.charly.market.favorite.model.dto.FavoriteRequest;
import com.charly.market.favorite.model.dto.FavoriteResponse;
import java.util.List;

public interface FavoriteService {
  void createFavorite(FavoriteRequest request);
  List<FavoriteResponse> getFavoritesByUser(Long userId);
  void removeFavorite(FavoriteRequest request);
}

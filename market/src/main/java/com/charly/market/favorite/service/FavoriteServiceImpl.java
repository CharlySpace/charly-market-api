package com.charly.market.favorite.service;

import com.charly.market.favorite.model.dto.FavoriteRequest;
import com.charly.market.favorite.model.dto.FavoriteResponse;
import com.charly.market.favorite.model.entity.Favorite;
import com.charly.market.favorite.repository.FavoriteRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteServiceImpl implements FavoriteService {

  private final FavoriteRepository favoriteRepository;

  @Override
  public void createFavorite(FavoriteRequest request) {
    Favorite favorite = Favorite.builder()
                                .userId(request.userId())
                                .auctionItemId(request.auctionItemId())
                                .build();

    favoriteRepository.save(favorite);
  }

  @Transactional(readOnly = true)
  @Override
  public List<FavoriteResponse> getFavoritesByUser(Long userId) {
    return favoriteRepository.findFavoritesByUserId(userId);
  }

  @Override
  public void removeFavorite(FavoriteRequest request) {
    favoriteRepository.deleteByUserIdAndAuctionItemId(request.userId(), request.auctionItemId());
  }
}

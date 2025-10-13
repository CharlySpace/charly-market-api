package com.charly.market.favorite.service;

import static org.junit.jupiter.api.Assertions.*;

import com.charly.market.favorite.model.dto.FavoriteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FavoriteServiceImplTest {

  @Autowired
  private FavoriteService favoriteService;

  @Test
  public void createFavorite() {

    for (int i = 1; i < 6; i++) {

      // given
      FavoriteRequest request = new FavoriteRequest(
          (long) i,
          (long) i
      );

      // when
      favoriteService.createFavorite(request);

      // then
      System.out.println(favoriteService.getFavoritesByUser((long) i));
    }
  }
}
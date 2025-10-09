package com.charly.market.favorite.controller;

import com.charly.market.favorite.model.dto.FavoriteRequest;
import com.charly.market.favorite.model.dto.FavoriteResponse;
import com.charly.market.favorite.service.FavoriteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/favorite")
public class FavoriteController {

  private final FavoriteService favoriteService;

  @PostMapping
  public ResponseEntity<String> createFavorite(@RequestBody FavoriteRequest request) {
    favoriteService.createFavorite(request);
    return ResponseEntity.ok("즐겨찾기 성공");
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<FavoriteResponse>> getFavorites(@PathVariable Long userId) {
    List<FavoriteResponse> favorites = favoriteService.getFavoritesByUser(userId);
    return ResponseEntity.ok(favorites);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteFavorite(@RequestBody FavoriteRequest request) {
    favoriteService.removeFavorite(request);
    return ResponseEntity.noContent().build();
  }
}

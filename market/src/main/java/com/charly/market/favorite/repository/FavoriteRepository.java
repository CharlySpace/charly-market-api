package com.charly.market.favorite.repository;

import com.charly.market.favorite.model.dto.FavoriteResponse;
import com.charly.market.favorite.model.entity.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
  @Query("""
    SELECT new com.charly.market.favorite.model.dto.FavoriteResponse(
        f.id, f.auctionItemId, a.title
    )
    FROM Favorite f
    JOIN AuctionItem a ON f.auctionItemId = a.id
    WHERE f.userId = :userId
    """)
  List<FavoriteResponse> findFavoritesByUserId(Long userId);
  void deleteByUserIdAndAuctionItemId(Long userId, Long auctionItemId);
}

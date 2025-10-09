package com.charly.market.favorite.model.dto;

public record FavoriteResponse(
    Long id,
    Long auctionItemId,
    String title
) {

}

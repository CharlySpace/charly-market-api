package com.charly.market.favorite.model.dto;

import jakarta.validation.constraints.NotBlank;

public record FavoriteRequest(
    @NotBlank Long userId,
    @NotBlank Long auctionItemId
) {

}

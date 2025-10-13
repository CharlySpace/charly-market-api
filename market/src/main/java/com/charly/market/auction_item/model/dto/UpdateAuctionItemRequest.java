package com.charly.market.auction_item.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateAuctionItemRequest(
        String title,
        String content,
        Long startingPrice,
        Long bidUnit,
        String address,
        Long categoryId
) {}

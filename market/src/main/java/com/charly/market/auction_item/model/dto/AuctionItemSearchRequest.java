package com.charly.market.auction_item.model.dto;

import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

public record AuctionItemSearchRequest(
        String keyword,
        Integer page,
        Integer size,
        Sort.Direction direction,
        String sortBy,
        String deliveryStatus,
        Integer startingPrice,
        Integer currentPrice,
        String categoryName,
        String bidStatus,
        Long seller
) {

    public AuctionItemSearchRequest {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 5;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
    }
}

package com.charly.market.auction_bid.model.dto;

import org.springframework.data.domain.Sort;

public record BidSearchRequest(
        Integer page,
        Integer size,
        Sort.Direction direction,
        String sortBy,
        Long bidAmount,
        String successStatus,
        Long auction,
        Long seller
) {

    public BidSearchRequest {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 5;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
    }
}

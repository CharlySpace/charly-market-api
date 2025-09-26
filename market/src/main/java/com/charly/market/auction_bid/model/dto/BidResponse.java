package com.charly.market.auction_bid.model.dto;

import java.time.LocalDateTime;

public record BidResponse(
        Long bidId,
        long bidAmount,
        LocalDateTime createAt,
        String successStatus,
        long auctionId,
        long userId
) {
}

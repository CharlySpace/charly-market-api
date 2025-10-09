package com.charly.market.auction_bid.model.dto;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.user.model.entity.User;

import java.time.LocalDateTime;

public record BidResponse(
        Long bidId,
        long bidAmount,
        LocalDateTime createAt,
        String successStatus,
        Long auction,
        Long seller
) {
}

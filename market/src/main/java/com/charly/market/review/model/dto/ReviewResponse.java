package com.charly.market.review.model.dto;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.user.model.entity.User;

import java.time.LocalDateTime;

public record ReviewResponse(
        double reviewStar,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String reviewStatus,
        Long reviewerId,
        Long revieweeId,
        Long auctionId
) {
}

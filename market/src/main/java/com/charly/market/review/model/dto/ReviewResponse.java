package com.charly.market.review.model.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        long reviewStar,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String reviewStatus,
        long reviewerId,
        long revieweeId,
        long auctionId
) {
}

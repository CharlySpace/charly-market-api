package com.charly.market.review.model.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        double reviewStar,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String reviewStatus,
        long reviewerId,
        long revieweeId,
        long auctionId
) {
}

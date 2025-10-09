package com.charly.market.review.model.dto;

public record CreateReviewRequest(
        Double reviewStar,
        String content,
        Long reviewerId,
        Long revieweeId,
        Long auction

) {
}

package com.charly.market.review.model.dto;

public record CreateReviewRequest(
        long reviewStar,
        String content

) {
}

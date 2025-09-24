package com.charly.market.review.model.dto;

public record CreateReviewRequest(
        double reviewStar,
        String content

) {
}

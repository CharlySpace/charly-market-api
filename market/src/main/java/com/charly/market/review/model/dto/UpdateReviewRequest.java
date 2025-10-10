package com.charly.market.review.model.dto;

public record UpdateReviewRequest(
        Double reviewStar,
        String content
) {

}

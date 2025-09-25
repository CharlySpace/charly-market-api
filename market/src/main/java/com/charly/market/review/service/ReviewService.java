package com.charly.market.review.service;

import com.charly.market.review.model.dto.CreateReviewRequest;
import com.charly.market.review.model.dto.ReviewResponse;
import com.charly.market.review.model.dto.UpdateReviewStarRequest;

import java.util.List;

public interface ReviewService {
    void create(CreateReviewRequest Review);
    List<ReviewResponse> reviewItemList();
    ReviewResponse reviewItemSearch(Long reviewId);
    void delete(Long reviewId);
    void changeReviewStar(Long reviewId , UpdateReviewStarRequest urs);
}

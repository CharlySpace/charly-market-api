package com.charly.market.review.service;

import com.charly.market.review.model.dto.CreateReviewRequest;
import com.charly.market.review.model.dto.ReviewResponse;
import com.charly.market.review.model.dto.ReviewSearchRequest;
import com.charly.market.review.model.dto.UpdateReviewRequest;

import org.springframework.data.domain.Page;


import java.util.List;

public interface ReviewService {
    void create(CreateReviewRequest Review);
    List<ReviewResponse> reviewItemList();
    Page<ReviewResponse> searchReview(ReviewSearchRequest request);
    ReviewResponse reviewItemSearch(Long reviewId);
    void delete(Long reviewId);
    void changeReview(Long reviewId , UpdateReviewRequest urs);
}

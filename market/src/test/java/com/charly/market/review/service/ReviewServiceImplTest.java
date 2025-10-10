package com.charly.market.review.service;

import com.charly.market.review.model.Review;
import com.charly.market.review.model.dto.CreateReviewRequest;
import com.charly.market.review.model.dto.ReviewResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReviewServiceImplTest {


    @Autowired
    private ReviewService reviewService;

    @Test
    public void createReview(){
        for (long i = 1; i < 6; i++) {
            long j = i+1;
            CreateReviewRequest createReviewRequest =
                    new CreateReviewRequest((double)i,"리뷰내용"+i ,i,j,i);

            reviewService.create(createReviewRequest);

            List<ReviewResponse> reviewResponses = reviewService.reviewItemList();

            System.out.println(reviewResponses);
        }
    }



}
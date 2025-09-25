package com.charly.market.review.controller;

import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.model.dto.UpdateAuctionItemContentRequest;
import com.charly.market.review.model.dto.CreateReviewRequest;
import com.charly.market.review.model.dto.ReviewResponse;
import com.charly.market.review.model.dto.UpdateReviewStarRequest;
import com.charly.market.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody CreateReviewRequest reviewRequest){
        String result = reviewRequest.toString();

        reviewService.create(reviewRequest);

        return ResponseEntity.ok("리뷰 등록" + result);
    }


    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findReviewList(){
        List<ReviewResponse> reviewResponses = reviewService.reviewItemList();

        return ResponseEntity.ok(reviewResponses);
    }


    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> findByReviewId(@PathVariable Long reviewId){
        ReviewResponse reviewResponse = reviewService.reviewItemSearch(reviewId);
        return ResponseEntity.ok(reviewResponse);
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long reviewId){
        reviewService.delete(reviewId);
        return ResponseEntity.ok("비활성화 성공");
    }


    @PatchMapping("/{reviewId}/reviewStar")
    public ResponseEntity<String> changeReviewStarRequest (@PathVariable Long reviewId ,@RequestBody UpdateReviewStarRequest urs){
        reviewService.changeReviewStar(reviewId,urs);

        return ResponseEntity.ok("변경 성공");
    }

}

package com.charly.market.review.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.review.model.dto.CreateReviewRequest;
import com.charly.market.review.model.dto.ReviewResponse;
import com.charly.market.review.model.dto.ReviewSearchRequest;
import com.charly.market.review.model.dto.UpdateReviewRequest;
import com.charly.market.review.service.ReviewService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

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


    // 그냥 페이징, 정렬없는 전체조회
    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findReviewList(){
        List<ReviewResponse> reviewResponses = reviewService.reviewItemList();

        return ResponseEntity.ok(reviewResponses);
    }

    // 페이징 및 검색 조건 추가
    @GetMapping("/search")
    public PageResponse<ReviewResponse> getUserLog(ReviewSearchRequest request) {
        return PageResponse.of(reviewService.searchReview(request));
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


    @PatchMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable Long reviewId,
            @RequestBody UpdateReviewRequest urs) {
        reviewService.changeReview(reviewId, urs);
        return ResponseEntity.ok("리뷰 수정 완료");
    }

}

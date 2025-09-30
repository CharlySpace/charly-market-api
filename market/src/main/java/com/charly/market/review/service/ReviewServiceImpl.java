package com.charly.market.review.service;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.notice.model.dto.ChangeContentRequest;
import com.charly.market.notice.model.entity.Notice;
import com.charly.market.review.model.Review;
import com.charly.market.review.model.dto.CreateReviewRequest;
import com.charly.market.review.model.dto.ReviewResponse;
import com.charly.market.review.model.dto.UpdateReviewStarRequest;
import com.charly.market.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public void create(CreateReviewRequest request) {
        Review review = Review.builder()
                .reviewStar(request.reviewStar())
                .content(request.content())
                .build();

        reviewRepository.save(review);

    }

    @Override
    public List<ReviewResponse> reviewItemList() {
        List<Review> reviewEntityList = reviewRepository.findAll();
        List<ReviewResponse> reviewResponses = new ArrayList<>();

        for (Review review : reviewEntityList) {

            ReviewResponse findAll = new ReviewResponse(

                    review.getReviewStar(),
                    review.getContent(),
                    review.getCreatedAt(),
                    review.getUpdatedAt(),
                    review.getReviewStatus(),
                    review.getReviewer(),
                    review.getReviewee(),
                    review.getAuction()
            );

            reviewResponses.add(findAll);

        }

        return reviewResponses;

    }

    @Override
    public ReviewResponse reviewItemSearch(Long reviewId) {
        Optional<Review> reviewItem = reviewRepository.findById(reviewId);

        return reviewItem.map(item -> new ReviewResponse(
                item.getReviewStar(),
                item.getContent(),
                item.getCreatedAt(),
                item.getUpdatedAt(),
                item.getReviewStatus(),
                item.getReviewer(),
                item.getReviewee(),
                item.getAuction()
        )).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Long reviewId) {
        Optional<Review> reviewItemOptional = reviewRepository.findById(reviewId);

        reviewItemOptional.ifPresent(review -> review.changePostingStatus());


    }

    @Transactional
    @Override
    public void changeReviewStar(Long reviewId, UpdateReviewStarRequest urs) {
        Optional<Review> reviewItem = reviewRepository.findById(reviewId);
        reviewItem.ifPresent(r -> r.changeReviewStar(urs.reviewStar()));

    }

//    @Transactional
//    @Override
//    public void changeReviewStar(UpdateReviewStarRequest req) {
//        Long id = 1L;
//        Review review = reviewRepository.findById();
//
//        review.changeReviewStar(req.newReviewStar());
//    }
}

package com.charly.market.review.model;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "review")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    private Double reviewStar;
    @NotNull
    private String content;


    private String reviewStatus = "Y";

    // fk
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewerId; // 작성자id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewee_id" , nullable = false)
    private User revieweeId; // 판매자id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id" , nullable = false)
    private AuctionItem auctionId; // 물품id

    public void changePostingStatus(){
        this.reviewStatus = "N";
    }

    public void changeReviewStar(double reviewStar){
        this.reviewStar = reviewStar;
    }


}

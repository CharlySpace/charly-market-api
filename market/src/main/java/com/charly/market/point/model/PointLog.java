package com.charly.market.point.model;

import com.charly.market.auction_bid.model.AuctionBid;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="point_log")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointLog extends BaseTimeEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private Long tradeAmount;

    @Column
    private String explanation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bid_id")
    private AuctionBid auctionBid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @Column
    private int pointAmount ;//point_amount


    public void changeExplanation(String newExplanation){
        this.explanation=newExplanation;
    }
}

package com.charly.market.auction_bid.model;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "auction_bid")
@NoArgsConstructor
@AllArgsConstructor
public class AuctionBid extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Long id; // bid_id
    private Long bidAmount; // 입찰가격
    private String successStatus; // 낙찰여부


    //fk
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = false)
    private AuctionItem auctionId; // 물품번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId; // 입찰자id

    public void successBid(){ this.successStatus="Y";}
}

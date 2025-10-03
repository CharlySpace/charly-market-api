package com.charly.market.auction_bid.repository;

import com.charly.market.auction_bid.model.AuctionBid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionBidRepository extends JpaRepository<AuctionBid,Long> , AuctionBidRepositoryCustom{
}

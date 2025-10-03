package com.charly.market.auction_bid.repository;

import com.charly.market.auction_bid.model.AuctionBid;
import com.charly.market.auction_bid.model.dto.BidSearchRequest;
import org.springframework.data.domain.Page;


public interface AuctionBidRepositoryCustom {
    Page<AuctionBid> search (BidSearchRequest request);
}

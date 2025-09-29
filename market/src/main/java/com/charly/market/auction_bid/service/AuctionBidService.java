package com.charly.market.auction_bid.service;


import com.charly.market.auction_bid.model.AuctionBid;
import com.charly.market.auction_bid.model.dto.BidResponse;
import com.charly.market.auction_bid.model.dto.CreateBidRequest;
import com.charly.market.auction_bid.model.dto.SuccessfulBidRequest;

import java.util.List;

public interface AuctionBidService {
    void create (CreateBidRequest bid);
    List<BidResponse> bidList ();
    BidResponse bidItemSearch(Long bidId);
    void delete(Long bidId);
    void successfulBid(Long bidId , SuccessfulBidRequest request);

}

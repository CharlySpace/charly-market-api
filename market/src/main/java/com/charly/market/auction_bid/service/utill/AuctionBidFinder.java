package com.charly.market.auction_bid.service.utill;

import com.charly.market.auction_bid.model.AuctionBid;
import com.charly.market.auction_bid.repository.AuctionBidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AuctionBidFinder {
    private final AuctionBidRepository auctionBidRepository;
    public AuctionBid getById(Long id) {
        return auctionBidRepository.findById(id).orElseThrow();
    }

}

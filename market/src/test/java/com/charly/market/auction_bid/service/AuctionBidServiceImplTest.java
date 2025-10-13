package com.charly.market.auction_bid.service;

import com.charly.market.auction_bid.model.dto.BidResponse;
import com.charly.market.auction_bid.model.dto.CreateBidRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuctionBidServiceImplTest {

    @Autowired
    AuctionBidService service;
    @Test
    public void createAuctionBidList(){
        for (long i = 1; i < 6; i++) {
            long bidAmount = 500 * i;
            CreateBidRequest request = new CreateBidRequest(bidAmount,i,i);

            service.create(request);
            List<BidResponse> responses = service.bidList();
            System.out.println(responses);

        }
    }

}
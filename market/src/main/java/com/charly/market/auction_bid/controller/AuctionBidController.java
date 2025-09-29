package com.charly.market.auction_bid.controller;

import com.charly.market.auction_bid.model.AuctionBid;
import com.charly.market.auction_bid.model.dto.BidResponse;
import com.charly.market.auction_bid.model.dto.CreateBidRequest;
import com.charly.market.auction_bid.model.dto.SuccessfulBidRequest;
import com.charly.market.auction_bid.service.AuctionBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auctionBid")
public class AuctionBidController {

    private final AuctionBidService bidService;

    @PostMapping
    public ResponseEntity<String> createBid(@RequestBody CreateBidRequest request){

        String result = request.toString();
        bidService.create(request);

        return ResponseEntity.ok("입찰성공" + result);
    }


    @GetMapping
    public ResponseEntity<List<BidResponse>> findBidList(){
        List<BidResponse> bidResponse = bidService.bidList();
        return ResponseEntity.ok(bidResponse);
    }


    @GetMapping("/{bidId}")
    public ResponseEntity<BidResponse> findById(@PathVariable Long bidId){
        BidResponse bidResponse = bidService.bidItemSearch(bidId);

        return ResponseEntity.ok(bidResponse);
    }


    @PatchMapping("/{bidId}/successStatus")
    public ResponseEntity<String> changeBidStatus(@PathVariable Long bidId , @RequestBody SuccessfulBidRequest request){

        bidService.successfulBid(bidId,request);

        return ResponseEntity.ok("낙찰 성공!");
    }
}

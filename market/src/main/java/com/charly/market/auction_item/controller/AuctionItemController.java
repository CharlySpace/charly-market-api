package com.charly.market.auction_item.controller;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.service.AuctionItemService;
import com.charly.market.auction_item.service.AuctionItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auctionItem")
public class AuctionItemController {

    private final AuctionItemService auctionItemService;

    @PostMapping
    public ResponseEntity<String> createAuctionItem(@RequestBody CreateAuctionItemRequest auctionItem){
        System.out.println(auctionItem.toString());

        auctionItemService.create(auctionItem);

        return ResponseEntity.ok("경매물품 등록");
    }

    @GetMapping
    public ResponseEntity<List<AuctionItem>> getAllAuctionItems() {
        List<AuctionItem> items = auctionItemService.getAllAuctionItems();
        return ResponseEntity.ok(items);
    };

}

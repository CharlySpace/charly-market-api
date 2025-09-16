package com.charly.market.auction_item.controller;

import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.service.AuctionItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auctionItem")
public class AuctionItemController {

    private final AuctionItemService auctionItemService;

    // 경매물품등록
    @PostMapping
    public ResponseEntity<String> createAuctionItem(@RequestBody CreateAuctionItemRequest auctionItem){
        System.out.println(auctionItem.toString());

        auctionItemService.create(auctionItem);

        return ResponseEntity.ok("경매물품 등록");
    }

    //전체 리스트 조회
    @GetMapping
    public ResponseEntity<List<AuctionItemResponse>> findAuctionList(){
        List<AuctionItemResponse> auctionItemList = auctionItemService.AuctionItemList();

        return ResponseEntity.ok(auctionItemList);
    }

    // id 값으로 1건 조회
    @GetMapping("/{auctionId}")
    public ResponseEntity<AuctionItemResponse> findByAuctionId(@PathVariable Long auctionId){
        AuctionItemResponse auctionItemResponse = auctionItemService.auctionItemSearch(auctionId);
        return ResponseEntity.ok(auctionItemResponse);
    }


    // 물품게시물 비활성화
    @DeleteMapping("/{auctionId}")
    public ResponseEntity<String> delete(@PathVariable Long auctionId){
        auctionItemService.delete(auctionId);
        return ResponseEntity.ok("비활성화 성공");
    }





}

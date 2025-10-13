package com.charly.market.auction_item.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.AuctionItemSearchRequest;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.model.dto.UpdateAuctionItemRequest;
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

    @GetMapping("/search")
    public PageResponse<AuctionItemResponse> searchAuctionList(AuctionItemSearchRequest request){
        return PageResponse.of(auctionItemService.auctionItemSearch(request));
    }

    // id 값으로 1건 조회
    @GetMapping("/{auction}")
    public ResponseEntity<AuctionItemResponse> findByauction(@PathVariable Long auction){
        AuctionItemResponse auctionItemResponse = auctionItemService.auctionItemSearch(auction);
        return ResponseEntity.ok(auctionItemResponse);
    }


    // 물품게시물 비활성화
    @DeleteMapping("/{auction}")
    public ResponseEntity<String> delete(@PathVariable Long auction){
        auctionItemService.delete(auction);
        return ResponseEntity.ok("비활성화 성공");
    }




    @PatchMapping("/{auction}/update")
    public ResponseEntity<String> changeUpdateAuctionItem (@PathVariable Long auction ,@RequestBody UpdateAuctionItemRequest request){
        auctionItemService.updateAuctionItem(auction,request);

        return ResponseEntity.ok("변경 성공");
    }



    @PatchMapping("/{auctionId}/status")
    public ResponseEntity<String> updateBidStatus(
            @PathVariable Long auctionId,
            @RequestParam String bidStatus  // B, F, Y, N
    ) {
        auctionItemService.updateBidStatus(auctionId, bidStatus);
        return ResponseEntity.ok("입찰 상태 변경성공");
    }




}

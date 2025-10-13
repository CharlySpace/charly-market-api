package com.charly.market.auction_item.service;


import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.AuctionItemSearchRequest;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.model.dto.UpdateAuctionItemRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuctionItemService {

    void create(CreateAuctionItemRequest auctionItem);
    List<AuctionItemResponse> AuctionItemList();
    Page<AuctionItemResponse> auctionItemSearch(AuctionItemSearchRequest request);
    AuctionItemResponse auctionItemSearch(Long auction);
    void delete(Long auction);

    void updateAuctionItem(Long auctionId, UpdateAuctionItemRequest request);
    void updateBidStatus(Long auctionId, String statusCode);
}

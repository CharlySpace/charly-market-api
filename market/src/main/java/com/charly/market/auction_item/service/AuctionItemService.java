package com.charly.market.auction_item.service;


import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;

import java.util.List;

public interface AuctionItemService {

    void create(CreateAuctionItemRequest auctionItem);
    List<AuctionItemResponse> AuctionItemList();
    AuctionItemResponse auctionItemSearch(Long auctionId);
    void delete(Long auctionId);
}

package com.charly.market.auction_item.service;


import com.charly.market.auction_item.model.dto.AuctionItemFindAll;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;

import java.util.List;

public interface AuctionItemService {

    void create(CreateAuctionItemRequest auctionItem);
//    public List<AuctionItemFindAll> getAllAuctionItems()
}

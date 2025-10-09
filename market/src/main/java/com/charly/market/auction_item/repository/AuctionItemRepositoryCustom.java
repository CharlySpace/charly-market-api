package com.charly.market.auction_item.repository;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.AuctionItemSearchRequest;
import org.springframework.data.domain.Page;

public interface AuctionItemRepositoryCustom {
    Page<AuctionItem> search (AuctionItemSearchRequest request);
}

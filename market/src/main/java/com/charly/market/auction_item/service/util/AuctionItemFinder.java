package com.charly.market.auction_item.service.util;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.repository.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionItemFinder {

    private final AuctionItemRepository auctionItemRepository;

    public AuctionItem getById(Long id) {
        return auctionItemRepository.findById(id).orElseThrow();
    }

}

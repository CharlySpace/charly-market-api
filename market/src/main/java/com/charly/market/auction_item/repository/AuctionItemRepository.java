package com.charly.market.auction_item.repository;

import com.charly.market.auction_item.model.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionItemRepository extends JpaRepository<AuctionItem,Long> {

}

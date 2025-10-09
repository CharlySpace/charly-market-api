package com.charly.market.auction_item.repository;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.AuctionItemSearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long>, AuctionItemRepositoryCustom {

//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("UPDATE AuctionItem a SET a.content = :content WHERE a.id = :auction")
//    int updateContent(@Param("auction") Long auction,
//                      @Param("content") String content);
}
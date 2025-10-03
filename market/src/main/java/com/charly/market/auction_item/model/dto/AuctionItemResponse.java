package com.charly.market.auction_item.model.dto;

import com.charly.market.user.model.entity.User;

import java.time.LocalDateTime;


public record AuctionItemResponse(
        Long id,
        String title,
        String content,
        String categoryName,
        Long startingPrice,
        Long CurrentPrice,
        Long bidUnit,
        String bidStatus,
        LocalDateTime auctionStartTime,
        LocalDateTime auctionEndTime,
        Long userId

) {


}




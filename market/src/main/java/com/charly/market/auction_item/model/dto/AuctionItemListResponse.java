package com.charly.market.auction_item.model.dto;

import java.time.LocalDateTime;


public record AuctionItemListResponse(
         String auctionTitle,
         String auctionContent,
         long startingPrice,
         long bidUnit,
         LocalDateTime auctionStartTime,
         LocalDateTime auctionEndTime,
         int userId
){


}




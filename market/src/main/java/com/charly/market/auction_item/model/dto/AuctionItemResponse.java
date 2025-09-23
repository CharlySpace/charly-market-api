package com.charly.market.auction_item.model.dto;

import java.time.LocalDateTime;


public record AuctionItemResponse(
         String title,
         String content,
         long startingPrice,
         long bidUnit,
         LocalDateTime auctionStartTime,
         LocalDateTime auctionEndTime,
         int userId
){


}




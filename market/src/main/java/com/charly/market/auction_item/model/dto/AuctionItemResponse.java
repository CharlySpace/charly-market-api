package com.charly.market.auction_item.model.dto;

import com.charly.market.user.model.entity.User;

import java.time.LocalDateTime;


public record AuctionItemResponse(
         String title,
         String content,
         long startingPrice,
         long bidUnit,
         LocalDateTime auctionStartTime,
         LocalDateTime auctionEndTime,
         User userId
){


}




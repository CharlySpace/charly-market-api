package com.charly.market.auction_item.model.dto;

public record CreateAuctionItemRequest(
        String auctionTitle,
        String auctionContent,
        long startingPrice,
        long bidUnit,
        String sellerAddress

) {
}

package com.charly.market.auction_item.model.dto;

public record CreateAuctionItemRequest(
        String title,
        String content,
        long startingPrice,
        long bidUnit,
        String address,// 판매자 주소
        Long categoryId,
        Long userId

) {
}

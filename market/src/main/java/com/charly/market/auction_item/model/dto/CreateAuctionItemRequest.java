package com.charly.market.auction_item.model.dto;

public record CreateAuctionItemRequest(
        String title,
        String content,
        Long startingPrice,
        Long bidUnit,
        String address,// 판매자 주소
        Long category,
        Long seller

) {
}

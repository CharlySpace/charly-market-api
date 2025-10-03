package com.charly.market.delivery.model.dto;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.user.model.entity.User;

import java.time.LocalDateTime;

public record DeliveryResponse(
        Long id,
        String address,
        long no,
        String deliveryStatus,
        LocalDateTime registeredAt,
        LocalDateTime finishedAt,
        Long sendId,
        Long receiverId,
        Long auctionId

) {
}

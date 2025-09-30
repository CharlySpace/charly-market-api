package com.charly.market.delivery.model.dto;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.user.model.entity.User;

import java.time.LocalDateTime;

public record DeliveryResponse(
        String address,
        long no,
        String deliveryStatus,
        LocalDateTime registeredAt,
        LocalDateTime finishedAt,
        User sendId,
        User receiverId,
        AuctionItem auctionId

) {
}

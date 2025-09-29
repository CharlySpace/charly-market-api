package com.charly.market.delivery.model.dto;

import java.time.LocalDateTime;

public record DeliveryResponse(
        String address,
        long no,
        String deliveryStatus,
        LocalDateTime registeredAt,
        LocalDateTime finishedAt,
        long sendId,
        long receiverId,
        long auctionId

) {
}

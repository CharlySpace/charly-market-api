package com.charly.market.delivery.model.dto;

import java.time.LocalDateTime;

public record CreateDeliveryRequest(
        long sendId,
        long receiverId,
        long auctionId
) {
}

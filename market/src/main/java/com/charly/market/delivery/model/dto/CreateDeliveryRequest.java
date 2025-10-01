package com.charly.market.delivery.model.dto;

import java.time.LocalDateTime;

public record CreateDeliveryRequest(
        Long sendId,
        Long receiverId,
        Long auctionId
) {
}

package com.charly.market.delivery.model.dto;

import java.time.LocalDateTime;

public record CreateDeliveryRequest(
        Long sender,
        Long receiver,
        Long auction
) {
}

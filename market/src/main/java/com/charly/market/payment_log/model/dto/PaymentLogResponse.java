package com.charly.market.payment_log.model.dto;

public record PaymentLogResponse(
        Long auction,
        Long bidId,
        String type,
        int paymentAmount,
        int conversionAmount,
        String gradeName,
        int pointAmount

) {
}

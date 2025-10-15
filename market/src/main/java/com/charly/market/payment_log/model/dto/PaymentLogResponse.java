package com.charly.market.payment_log.model.dto;


public record PaymentLogResponse(
    String type,
    Long auctionId,
    Long userId,
    int paymentAmount,
    int conversionAmount,
    String gradeName,
    int pointAmount

) {

}

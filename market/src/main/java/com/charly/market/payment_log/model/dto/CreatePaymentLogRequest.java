package com.charly.market.payment_log.model.dto;


import jakarta.validation.constraints.NotNull;

public record CreatePaymentLogRequest(
    @NotNull String type,
    @NotNull Long accountId,
    @NotNull Long userId,
    @NotNull int paymentAmount,
    @NotNull int conversionAmount,
    @NotNull String gradeName
) {

}

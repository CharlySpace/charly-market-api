package com.charly.market.account.model.dto;

public record AccountResponse(
        String bankName,
        String accountNumber,
        String bankOwner,
        Long seller
) {
}

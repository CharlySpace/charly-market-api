package com.charly.market.account.model.dto;


import jakarta.validation.constraints.NotNull;

public record AccountRequest(
    @NotNull String bankName,
    @NotNull String accountNumber,
    @NotNull String bankOwner,
    @NotNull Long userId
) {

}

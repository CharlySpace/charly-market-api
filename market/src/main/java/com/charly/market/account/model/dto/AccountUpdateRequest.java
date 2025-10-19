package com.charly.market.account.model.dto;


import jakarta.validation.constraints.NotNull;

public record AccountUpdateRequest(
    @NotNull String bankName,
    @NotNull String accountNumber,
    @NotNull String bankOwner

) {

}

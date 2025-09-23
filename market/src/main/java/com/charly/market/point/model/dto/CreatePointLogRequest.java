package com.charly.market.point.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePointLogRequest(
        @NotBlank String type,
        @NotBlank Long tradeAmount,
        @NotBlank String explanation,
        @NotBlank int pointAmount
) {
}

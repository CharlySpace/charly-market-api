package com.charly.market.point.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePointLogRequest(
        @NotBlank String trade_type,
        @NotBlank Long trade_amount,
        @NotBlank String trade_explanation,
        @NotBlank Long point_amount
) {
}

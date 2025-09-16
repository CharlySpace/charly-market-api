package com.charly.market.notice.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateContentRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String status
) {
}

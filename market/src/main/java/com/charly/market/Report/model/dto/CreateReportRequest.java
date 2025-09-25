package com.charly.market.Report.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateReportRequest(
        @NotBlank String content,
        @NotBlank String status,
        @NotBlank String action
) {
}

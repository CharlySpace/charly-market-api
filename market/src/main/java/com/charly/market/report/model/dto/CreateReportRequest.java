package com.charly.market.report.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateReportRequest(
        @NotBlank Long categoryId,
        @NotBlank Long auctionId,
        @NotBlank String content,
        @NotBlank String status,
        @NotBlank Long reporterId,
        String action,
        Long adminId
) {
}

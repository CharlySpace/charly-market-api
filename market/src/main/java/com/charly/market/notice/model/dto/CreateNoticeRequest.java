package com.charly.market.notice.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateNoticeRequest(
        @NotBlank String noticeTitle,
        @NotBlank String noticeContent,
        @NotBlank char noticeStatus
) {
}

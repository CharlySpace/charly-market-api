package com.charly.market.notice.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateNoticeRequest(
        @NotBlank String notice_title,
        @NotBlank String notice_content,
        @NotBlank char notice_status
) {
}

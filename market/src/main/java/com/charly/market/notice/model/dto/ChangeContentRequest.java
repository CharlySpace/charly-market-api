package com.charly.market.notice.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangeContentRequest(
        // 조회ID 나중에 fk로 수정 (관리자 인증)
        @NotBlank long contentId,
        @NotBlank String content
) {
}

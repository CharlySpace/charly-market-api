package com.charly.market.notice.model.dto;

import org.springframework.data.domain.Sort;

public record NoticeSearchRequest(
        Integer page,
        Integer size,
        Sort.Direction direction,
        String sortBy,
        String status,
        String titleKeyword //제목 키워드 검색
) {
    public NoticeSearchRequest {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 10;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
        if (status == null || status.isBlank()) status = "Y"; // 기본 활성 공지만 조회
        if (titleKeyword != null && titleKeyword.isBlank()) titleKeyword = null; // 빈 문자열은 null 처리
    }
}

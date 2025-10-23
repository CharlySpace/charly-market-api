package com.charly.market.report.model.dto;

import org.springframework.data.domain.Sort;

    public record ReportSearchRequest(
            Integer page,
            Integer size,
            Sort.Direction direction,
            String sortBy,
            String status,
            Long categoryId,
            String auctionTitleKeyword,
            String contentKeyword,
            Long reportUserId,
            Long adminUserId
    ) {
        public ReportSearchRequest {
            if (page == null || page < 0) page = 0;
            if (size == null || size <= 0) size = 10;
            if (direction == null) direction = Sort.Direction.DESC;
            if (sortBy == null || sortBy.isBlank()) sortBy = "id"; // 정렬 기준 컬럼이 없거나 공백이면 id 기준으로 정렬, 공백으로 들어와도 is Blank()로 방지 가능.
            if (status == null || status.isBlank()) status = "Y"; // 기본 활성 공지만 조회
            if (auctionTitleKeyword != null && auctionTitleKeyword.isBlank()) auctionTitleKeyword = null; // 빈 문자열은 null 처리
            if (contentKeyword != null && contentKeyword.isBlank()) contentKeyword = null;

        }

    }

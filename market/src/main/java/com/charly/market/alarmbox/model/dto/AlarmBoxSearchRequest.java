package com.charly.market.alarmbox.model.dto;

import org.springframework.data.domain.Sort;

public record AlarmBoxSearchRequest(


        Integer page,
        Integer size,
        Sort.Direction direction,
        String sortBy,        // createdAt | id | status (화이트리스트 적용)
        Long userId,          // 필수
        Long auctionId,
        String status,        // Y|N
        String content

        //String keyword,       // content LIKE 검색, 유저용 알람함에는 불필요.
) {
    // 허용 정렬 필드 화이트리스트 (SQL injection 방지)
    //private static final Set<String> ALLOWED_SORT = Set.of("createdAt", "id", "status");

    public AlarmBoxSearchRequest {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 10;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "createdAt";
    }
}




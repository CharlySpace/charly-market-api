package com.charly.market.review.model.dto;

import com.charly.market.global.model.BaseTimeEntity;
import org.springframework.data.domain.Sort;

public record ReviewSearchRequest(
        Long reviewerId,
        Long revieweeId,
        Double reviewStar,
        String keyword,
        Integer page,
        Integer size,
        Sort.Direction direction,
        String sortBy
) {

    public ReviewSearchRequest {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 5;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
    }

}

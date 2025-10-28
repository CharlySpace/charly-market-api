package com.charly.market.inquiry.model.dto;


import org.springframework.data.domain.Sort;


public record InquirySearchRequest (

        Integer page,
        Integer size,
        Sort.Direction direction,
        String sortBy,
        String titleKeyword,
        String content,
        String status,
        String answer,
        Long userId,
        Long adminUserId


) {
    public InquirySearchRequest {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 10;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
        if (status == null || status.isBlank()) status = "Y";
        if (titleKeyword != null && titleKeyword.isBlank()) titleKeyword = null;
    }
}

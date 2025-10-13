package com.charly.market.payment_log.model.dto;

import org.springframework.data.domain.Sort;

public record PaymentLogSearchRequest(
        Long userId,
        Long accountId,
        String type,
        Integer paymentAmount,
        Integer size,
        Integer page,
        Sort.Direction direction,
        String sortBy

){
    public PaymentLogSearchRequest{
    if (page == null || page < 0) page = 0;
    if (size == null || size <= 0) size = 5;
    if (direction == null) direction = Sort.Direction.DESC;
    if (sortBy == null || sortBy.isBlank()) sortBy = "id";
    }
}

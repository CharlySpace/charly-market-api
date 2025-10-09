package com.charly.market.delivery.model.dto;

import org.springframework.data.domain.Sort;

public record DeliverySearchRequest (
        Integer page,
        Integer size,
        Sort.Direction direction,
        String sortBy,
        String deliveryStatus,
        Long receiver,
        Long sender
){
    public DeliverySearchRequest {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 5;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
    }
}

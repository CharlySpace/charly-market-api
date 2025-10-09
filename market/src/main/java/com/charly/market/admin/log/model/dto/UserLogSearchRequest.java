package com.charly.market.admin.log.model.dto;

import org.springframework.data.domain.Sort;

public record UserLogSearchRequest(
    Long seller,
    String keyword,
    String columnName,
    Integer page,
    Integer size,
    Sort.Direction direction,
    String sortBy
) {
  public UserLogSearchRequest {
    if (page == null || page < 0) page = 0;
    if (size == null || size <= 0) size = 5;
    if (direction == null) direction = Sort.Direction.DESC;
    if (sortBy == null || sortBy.isBlank()) sortBy = "id";
  }
}

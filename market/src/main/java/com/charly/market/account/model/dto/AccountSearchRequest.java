package com.charly.market.account.model.dto;

import org.springframework.data.domain.Sort;

public record AccountSearchRequest(
    String bankName,
    Long userId,
    Integer size,
    Integer page,
    Sort.Direction direction,
    String sortBy
) {

  public AccountSearchRequest {
    if (page == null || page < 0) {
      page = 0;
    }
    if (size == null || size <= 0) {
      size = 5;
    }
    if (direction == null) {
      direction = Sort.Direction.DESC;
    }
    if (sortBy == null || sortBy.isBlank()) {
      sortBy = "id";
    }
  }
}

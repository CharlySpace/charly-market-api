package com.charly.market.admin.log.model.dto;

public record UserLogResponse(
    Long id,
    Long seller,
    String logContent,
    String columnName
) {}

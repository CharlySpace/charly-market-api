package com.charly.market.admin.log.model.dto;

public record UserLogResponse(
    Long id,
    Long userId,
    String logContent,
    String columnName
) {}

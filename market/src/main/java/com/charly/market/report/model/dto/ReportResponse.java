package com.charly.market.report.model.dto;

public record ReportResponse (
        Long categoryId,
        Long auctionId,
        String content,
        String status,
        Long userId,
        String action,
        Long reporterId
){

}

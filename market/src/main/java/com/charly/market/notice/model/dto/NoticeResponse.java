package com.charly.market.notice.model.dto;

public record NoticeResponse(
        String noticeTitle,
        String noticeContent,
        char noticeStatus
){

}

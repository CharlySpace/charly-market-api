package com.charly.market.notice.model.dto;

public record NoticeListResponse(
        String notice_title,
        String notice_content,
        char notice_status
){

}

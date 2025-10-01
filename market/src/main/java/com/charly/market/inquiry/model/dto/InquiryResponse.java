package com.charly.market.inquiry.model.dto;

public record InquiryResponse (
        Long userId,
        String title,
        String content,
        char status,
        String answer
){

}

package com.charly.market.inquiry.model.dto;

public record InquiryResponse (
        Long user_id,
        String title,
        String content,
        char status,
        String answer
){

}

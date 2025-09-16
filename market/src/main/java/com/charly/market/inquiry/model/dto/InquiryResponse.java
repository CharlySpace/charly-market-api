package com.charly.market.inquiry.model.dto;

public record InquiryResponse (
        String title,
        String content,
        char status,
        String answer
){

}

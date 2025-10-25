package com.charly.market.inquiry.model.dto;

public record InquiryResponse (
        Long seller,
        String title,
        String content,
        String status,
        String answer
){

}

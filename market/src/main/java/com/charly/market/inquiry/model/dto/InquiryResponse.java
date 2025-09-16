package com.charly.market.inquiry.model.dto;

public record InquiryResponse (
        String inquiryTitle,
        String inquiryContent,
        char inquiryStatus,
        String inquiryAnswer
){

}

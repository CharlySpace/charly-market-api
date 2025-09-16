package com.charly.market.inquiry.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateInquiryRequest (
        @NotBlank String inquiryTitle,
        @NotBlank String inquiryContent,
        @NotBlank char inquiryStatus,
        @NotBlank String inquiryAnswer
){
}

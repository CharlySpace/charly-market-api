package com.charly.market.inquiry.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateInquiryRequest (
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank char status,
        @NotBlank String answer,
        @NotBlank Long seller
){
}

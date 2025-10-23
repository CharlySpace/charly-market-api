package com.charly.market.alarmbox.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmBoxResponse(

        Long id,              // 식별자(클릭/읽음처리/링크에 필요)
        String status,        // Y/N
        String content,       // 내용
        Long alarmTemplateId, // 템플릿 연결
        Long auctionItemId   // 경매글 연결(없을 수 있음)
){

}

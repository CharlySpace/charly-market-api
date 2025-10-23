package com.charly.market.alarmbox.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAlarmBoxRequest(



        @NotBlank String status,
        @NotBlank String content,
        @NotBlank Long userId,
        @NotBlank Long alarmTemplateId,
        @NotBlank Long auctionItemId
// alarmbox 엔티티 보고 필요한거 넣어야함

) {

}



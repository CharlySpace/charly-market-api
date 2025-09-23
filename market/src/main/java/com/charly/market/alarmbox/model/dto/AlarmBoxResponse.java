package com.charly.market.alarmbox.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmBoxResponse(

        @NotBlank String status,
        @NotBlank String content
){

}

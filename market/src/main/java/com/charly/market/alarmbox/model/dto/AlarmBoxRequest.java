package com.charly.market.alarmbox.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmBoxRequest(


        @NotBlank String status,
        @NotBlank String content


) {

}



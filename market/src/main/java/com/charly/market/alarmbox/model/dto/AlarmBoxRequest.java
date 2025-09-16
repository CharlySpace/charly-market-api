package com.charly.market.alarmbox.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmBoxRequest(

        @NotBlank String alarm_check,
        @NotBlank String alarm_content


) {

}



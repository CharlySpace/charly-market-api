package com.charly.market.alarmbox.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmBoxDtos(

        @NotBlank int alarm_box_id,
        @NotBlank String alarm_check,
        @NotBlank String alarm_content
) {

}



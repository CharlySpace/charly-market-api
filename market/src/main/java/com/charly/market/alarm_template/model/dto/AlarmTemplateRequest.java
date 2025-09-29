package com.charly.market.alarm_template.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmTemplateRequest (

        @NotBlank String status,
        @NotBlank String content
){

}

package com.charly.market.alarm_template.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmTemplateChangeContentRequest (


        @NotBlank Long id,
        @NotBlank String content
){


}

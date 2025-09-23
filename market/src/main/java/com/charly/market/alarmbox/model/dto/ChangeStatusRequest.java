package com.charly.market.alarmbox.model.dto;

import jakarta.validation.constraints.NotBlank;

    public record ChangeStatusRequest(

            @NotBlank long id,
            @NotBlank String status

    ){

    }

package com.charly.market.user.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
    @NotBlank String username,
    @NotBlank String currentPassword,
    @NotBlank String newPassword
) {

}

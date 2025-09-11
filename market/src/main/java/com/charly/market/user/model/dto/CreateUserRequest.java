package com.charly.market.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequest(
    @NotBlank String id,
    @NotBlank String userPassword,
    @NotBlank String userName,
    @NotBlank String userNickname,
    @NotBlank @Email String userEmail,
    @NotBlank @Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$") String userPhone
) {
}

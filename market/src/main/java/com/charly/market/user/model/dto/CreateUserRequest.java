package com.charly.market.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequest(
    @NotBlank String id,
    @NotBlank String password,
    @NotBlank String name,
    @NotBlank String nickname,
    @NotBlank @Email String email,
    @NotBlank @Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$") String phone
) {
}

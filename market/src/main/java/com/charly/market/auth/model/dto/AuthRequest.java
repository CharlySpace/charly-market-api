package com.charly.market.auth.model.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
  public record LoginRequest(
      @NotBlank String username,
      @NotBlank String password,
      String deviceId // 간단히 문자열 기기ID
  ) {}
  public record TokenResponse(
      String accessToken, long accessExpiresInSeconds,
      String refreshToken, long refreshExpiresInSeconds
  ) {}
  public record RefreshRequest(
      @NotBlank String refreshToken,
      String deviceId
  ) {}
}

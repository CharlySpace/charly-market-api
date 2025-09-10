package com.charly.market.auth.service;

import com.charly.market.auth.dto.AuthDtos.LoginRequest;
import com.charly.market.auth.dto.AuthDtos.RefreshRequest;
import com.charly.market.auth.dto.AuthDtos.TokenResponse;

public interface AuthService {
  TokenResponse login(LoginRequest req);
  TokenResponse refresh(RefreshRequest req);
  void logout(Long userId, String deviceId, String currentAccessJti);
}

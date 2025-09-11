package com.charly.market.auth.service;

import com.charly.market.auth.model.dto.AuthDtos.LoginRequest;
import com.charly.market.auth.model.dto.AuthDtos.RefreshRequest;
import com.charly.market.auth.model.dto.AuthDtos.TokenResponse;

public interface AuthService {
  TokenResponse login(LoginRequest req);
  TokenResponse refresh(RefreshRequest req);
  void logout(Long userId, String deviceId, String currentAccessJti);
}

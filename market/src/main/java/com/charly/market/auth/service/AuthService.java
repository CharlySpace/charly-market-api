package com.charly.market.auth.service;

import com.charly.market.auth.model.dto.AuthRequest.LoginRequest;
import com.charly.market.auth.model.dto.AuthRequest.RefreshRequest;
import com.charly.market.auth.model.dto.AuthRequest.TokenResponse;

public interface AuthService {
  TokenResponse login(LoginRequest req);
  TokenResponse refresh(RefreshRequest req);
  void logout(String username, String deviceId, String currentAccessJti);
}

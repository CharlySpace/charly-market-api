package com.charly.market.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record OAuthLoginRequest(String code, String deviceId) {}

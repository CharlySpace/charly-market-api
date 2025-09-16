package com.charly.market.user.model.dto;

import com.charly.market.user.model.entity.User;

public record UserResponse(
    String id,
    String name,
    String nickname,
    String email,
    String phone,
    int balance,
    int tradeCount,
    int storedPoint
){
  public static UserResponse from(User u) {
    return new UserResponse(u.getUsername(), u.getName(),
                            u.getNickname(), u.getEmail(),
                            u.getPhone(), u.getBalance(),
                            u.getTradeCount(), u.getStoredPoint());
  }
}
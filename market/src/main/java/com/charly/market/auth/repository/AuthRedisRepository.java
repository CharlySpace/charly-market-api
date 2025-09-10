package com.charly.market.auth.repository;

import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRedisRepository {

  private final StringRedisTemplate redis;

  private static String refreshKey(Long userId, String deviceId) {
    return "refresh:%d:%s".formatted(userId, deviceId);
  }
  private static String blKey(String jti) { return "bl:access:%s".formatted(jti); }

  public void saveRefresh(Long userId, String deviceId, String refreshToken, Duration ttl) {
    redis.opsForValue().set(refreshKey(userId, deviceId), refreshToken, ttl);
  }

  public Optional<String> getRefresh(Long userId, String deviceId) {
    String val = redis.opsForValue().get(refreshKey(userId, deviceId));
    return Optional.ofNullable(val);
  }

  public void deleteRefresh(Long userId, String deviceId) {
    redis.delete(refreshKey(userId, deviceId));
  }

  public void blacklistAccessJti(String jti, Duration ttl) {
    redis.opsForValue().set(blKey(jti), "1", ttl);
  }

  public boolean isBlacklisted(String jti) {
    return redis.hasKey(blKey(jti));
  }
}

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

  private static String refreshKey(String username, String deviceId) {
    return "refresh:%s:%s".formatted(username, deviceId);
  }
  private static String blKey(String jti) { return "bl:access:%s".formatted(jti); }

  public void saveRefresh(String username, String deviceId, String refreshToken, Duration ttl) {
    redis.opsForValue().set(refreshKey(username, deviceId), refreshToken, ttl);
  }

  public Optional<String> getRefresh(String username, String deviceId) {
    String val = redis.opsForValue().get(refreshKey(username, deviceId));
    return Optional.ofNullable(val);
  }

  public void deleteRefresh(String username, String deviceId) {
    redis.delete(refreshKey(username, deviceId));
  }

  public void blacklistAccessJti(String jti, Duration ttl) {
    redis.opsForValue().set(blKey(jti), "1", ttl);
  }

  public boolean isBlacklisted(String jti) {
    return redis.hasKey(blKey(jti));
  }
}

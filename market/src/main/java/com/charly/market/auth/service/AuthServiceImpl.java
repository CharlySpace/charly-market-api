package com.charly.market.auth.service;

import com.charly.market.auth.model.dto.AuthRequest.LoginRequest;
import com.charly.market.auth.model.dto.AuthRequest.RefreshRequest;
import com.charly.market.auth.model.dto.AuthRequest.TokenResponse;
import com.charly.market.auth.repository.AuthRedisRepository;
import com.charly.market.global.security.JwtProperties;
import com.charly.market.global.security.JwtTokenProvider;
import com.charly.market.global.security.JwtTokenProvider.TokenPair;
import com.charly.market.user.model.entity.User;
import com.charly.market.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;
  private final JwtProperties props;
  private final AuthRedisRepository redis;

  public TokenResponse login(LoginRequest req) {
    User user = userRepository.findByUsername(req.username());
    if (!passwordEncoder.matches(req.password(), user.getPassword())) {
      throw new IllegalArgumentException("INVALID_CREDENTIALS");
    }

    TokenPair pair = tokenProvider.createTokens(user.getUsername(), String.valueOf(user.getRole()));

    // Refresh 저장(디바이스별 세션 1개)
    redis.saveRefresh(user.getUsername(), pair.refreshToken(), props.refreshTtl());

    return new TokenResponse(
        pair.accessToken(), props.accessTtl().toSeconds(),
        pair.refreshToken(), props.refreshTtl().toSeconds()
    );
  }

  public TokenResponse refresh(RefreshRequest req) {
    // 1) 파싱·검증
    Jws<Claims> jws = tokenProvider.parseAndValidate(req.refreshToken());
    if (!tokenProvider.isRefreshToken(jws)) {
      throw new IllegalArgumentException("NOT_REFRESH_TOKEN");
    }
    String username = tokenProvider.getUsername(jws);

    // 2) Redis 세션 검증(저장된 refresh와 동일한가)
    String saved = redis.getRefresh(username)
                        .orElseThrow(() -> new IllegalStateException("NO_REFRESH_SESSION"));
    if (!saved.equals(req.refreshToken())) {
      throw new IllegalStateException("ROTATED_OR_REVOKED_REFRESH");
    }

    // 3) 유저 정보 로드 후 새 토큰 발급 (회전)
    User user = userRepository.findByUsername(username);

//    TokenPair newPair = tokenProvider.createTokens(user.getseller(), user.getUserEmail(), roles);
    TokenPair newPair = tokenProvider.createTokens(user.getUsername(), String.valueOf(user.getRole()));

    // 4) 기존 refresh 폐기 → 새 refresh 저장(회전)
    redis.saveRefresh(user.getUsername(), newPair.refreshToken(), props.refreshTtl());

    return new TokenResponse(
        newPair.accessToken(), props.accessTtl().toSeconds(),
        newPair.refreshToken(), props.refreshTtl().toSeconds()
    );
  }

  public void logout(String username, String currentAccessJti) {
    // 1) 세션 제거
    redis.deleteRefresh(username);
    // 2) 현재 Access 블랙리스트(남은 TTL 만큼)
    Duration remaining = props.accessTtl(); // 엄밀히는 exp-현재 시간을 계산해도 됨
    if (currentAccessJti != null) {
      redis.blacklistAccessJti(currentAccessJti, remaining);
    }
  }
}

package com.charly.market.auth.controller;

import com.charly.market.auth.model.dto.AuthRequest;
import com.charly.market.auth.model.dto.AuthRequest.TokenResponse;
import com.charly.market.auth.model.dto.OAuthLoginRequest;
import com.charly.market.auth.service.AuthService;
import com.charly.market.auth.service.OAuth2Service;
import com.charly.market.global.security.JwtTokenProvider;
import com.charly.market.global.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;
  private final JwtTokenProvider tokenProvider;
  private final OAuth2Service oAuth2Service;

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody AuthRequest.LoginRequest req) {
    return ResponseEntity.ok(authService.login(req));
  }

  @PostMapping("/login/oauth2/{provider}")
  public ResponseEntity<TokenResponse> oauthLogin(
      @PathVariable String provider,
      @RequestBody OAuthLoginRequest req
  ) {
    return ResponseEntity.ok(oAuth2Service.login(provider, req));
  }

  @PostMapping("/refresh")
  public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody AuthRequest.RefreshRequest req) {
    return ResponseEntity.ok(authService.refresh(req));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(
      @RequestHeader(name = "Authorization", required = false) String authHeader,
      @RequestParam String deviceId,
      Authentication auth) {

    String jti = null;
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      try {
        Jws<Claims> jws = tokenProvider.parseAndValidate(authHeader.substring(7).trim());
        if (tokenProvider.isAccessToken(jws)) {
          jti = tokenProvider.getJti(jws);
        }
      } catch (Exception ignored) {}
    }

    UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
    String username = principal.username();

    authService.logout(username, deviceId, jti);
    return ResponseEntity.ok(Map.of("success", true));
  }

}

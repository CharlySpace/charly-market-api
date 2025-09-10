package com.charly.market.global.security;

import com.charly.market.auth.repository.AuthRedisRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtTokenProvider tokenProvider;
  private final AuthRedisRepository authRedis;
  private final AntPathMatcher matcher = new AntPathMatcher();

  private static final String[] PUBLIC = {
      "/api/v1/auth/**", "/actuator/**"
  };

  public JwtAuthFilter(JwtTokenProvider tokenProvider, AuthRedisRepository authRedis) {
    this.tokenProvider = tokenProvider;
    this.authRedis = authRedis;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    for (String pattern : PUBLIC) {
      if (matcher.match(pattern, path)) return true;
    }
    return false;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {

    try {
      String header = req.getHeader(HttpHeaders.AUTHORIZATION);
      if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7).trim();
        Jws<Claims> jws = tokenProvider.parseAndValidate(token);

        if (!tokenProvider.isAccessToken(jws)) {
          throw new RuntimeException("NOT_ACCESS_TOKEN");
        }
        String jti = tokenProvider.getJti(jws);
        if (authRedis.isBlacklisted(jti)) {
          throw new RuntimeException("ACCESS_BLACKLISTED");
        }
        Authentication auth = tokenProvider.toAuthentication(jws);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception e) {
      SecurityContextHolder.clearContext();
    }
    chain.doFilter(req, res);
  }
}

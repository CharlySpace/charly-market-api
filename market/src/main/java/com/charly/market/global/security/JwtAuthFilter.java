package com.charly.market.global.security;

import com.charly.market.auth.repository.AuthRedisRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.PathContainer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.pattern.PathPatternParser;

public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtTokenProvider tokenProvider;
  private final AuthRedisRepository authRedis;
  private static final PathPatternParser parser = new PathPatternParser();

  public JwtAuthFilter(JwtTokenProvider tokenProvider, AuthRedisRepository authRedis) {
    this.tokenProvider = tokenProvider;
    this.authRedis = authRedis;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return Stream.of("/test/**", "/api/v1/**", "/actuator/**", "/favicon.ico", "/.well-known/**")
                 .anyMatch(pattern -> parser.parse(pattern).matches(PathContainer.parsePath(path)));
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

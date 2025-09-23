package com.charly.market.global.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private final JwtProperties props;
  private final SecretKey hmacKey;

  public JwtTokenProvider(JwtProperties props) {
    this.props = props;
    this.hmacKey = Keys.hmacShaKeyFor(props.secret().getBytes(StandardCharsets.UTF_8));
  }

  public TokenPair createTokens(String username, String role) {
    String jti = UUID.randomUUID().toString();
    String access = buildToken(username, role, props.accessTtl(), jti, "access");
    String refresh = buildToken(username, role, props.refreshTtl(), UUID.randomUUID().toString(), "refresh");
    return new TokenPair(access, refresh, jti);
  }

  public String createAccessOnly(String username, String role) {
    return buildToken(username, role, props.accessTtl(), UUID.randomUUID().toString(), "access");

  }

  private String buildToken(String username, String role, java.time.Duration ttl, String jti, String typ) {
    Instant now = Instant.now();
    return Jwts.builder()
               .header().type("JWT").and()
               .issuer(props.issuer())
               .subject(username)
               .audience().add("smerp-api").and()
               .issuedAt(Date.from(now))
               .expiration(Date.from(now.plus(ttl)))
               .id(jti)
               .claim("role", role)
               .claim("typ", typ)
               .signWith(hmacKey)
               .compact();
  }

  public Jws<Claims> parseAndValidate(String token) {
    return Jwts.parser().verifyWith(hmacKey).build().parseSignedClaims(token);
  }

  public boolean isRefreshToken(Jws<Claims> jws) {
    return "refresh".equals(jws.getPayload().get("typ", String.class));
  }

  public boolean isAccessToken(Jws<Claims> jws) {
    return "access".equals(jws.getPayload().get("typ", String.class));
  }

  public String getJti(Jws<Claims> jws) {
    return jws.getPayload().getId();
  }

  public String getUsername(Jws<Claims> jws) {
    return jws.getPayload().getSubject();
  }

  public Authentication toAuthentication(Jws<Claims> jws) {
    String username = getUsername(jws);
    String email = jws.getPayload().get("email", String.class);
    String role = jws.getPayload().get("role", String.class);
    UserPrincipal principal = new UserPrincipal(username, email, role);
    return new UsernamePasswordAuthenticationToken(principal, null);
  }

  public record TokenPair(String accessToken, String refreshToken, String accessJti) {}
}
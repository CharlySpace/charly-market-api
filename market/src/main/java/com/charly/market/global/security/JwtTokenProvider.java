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

  public TokenPair createTokens(Long userId, String email) {
    String jti = UUID.randomUUID().toString();
//    String access = buildToken(userId, email, roles, props.accessTtl(), jti, "access");
    String access = buildToken(userId, email, props.accessTtl(), jti, "access");
//    String refresh = buildToken(userId, email, roles, props.refreshTtl(), UUID.randomUUID().toString(), "refresh");
    String refresh = buildToken(userId, email, props.refreshTtl(), UUID.randomUUID().toString(), "refresh");
    return new TokenPair(access, refresh, jti);
  }

  public String createAccessOnly(Long userId, String email) {
//    return buildToken(userId, email, roles, props.accessTtl(), UUID.randomUUID().toString(), "access");
    return buildToken(userId, email, props.accessTtl(), UUID.randomUUID().toString(), "access");

  }

  private String buildToken(Long userId, String email,
      java.time.Duration ttl, String jti, String typ) {
    Instant now = Instant.now();
    return Jwts.builder()
               .header().type("JWT").and()
               .issuer(props.issuer())
               .subject(String.valueOf(userId))
               .audience().add("smerp-api").and()
               .issuedAt(Date.from(now))
               .expiration(Date.from(now.plus(ttl)))
               .id(jti)
               .claim("email", email)
//               .claim("roles", roles)
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

  public Long getUserId(Jws<Claims> jws) {
    return Long.valueOf(jws.getPayload().getSubject());
  }

  public Authentication toAuthentication(Jws<Claims> jws) {
    Long userId = getUserId(jws);
    String email = jws.getPayload().get("email", String.class);
    @SuppressWarnings("unchecked")
    List<String> roles = jws.getPayload().get("roles", List.class);
    Collection<SimpleGrantedAuthority> auths =
        roles == null ? List.of() : roles.stream().map(SimpleGrantedAuthority::new).toList();

    UserPrincipal principal = new UserPrincipal(userId, email, roles);
    return new UsernamePasswordAuthenticationToken(principal, null, auths);
  }

  public record TokenPair(String accessToken, String refreshToken, String accessJti) {}
}
package com.charly.market.auth.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.charly.market.auth.model.dto.AuthRequest.TokenResponse;
import com.charly.market.auth.model.dto.OAuthLoginRequest;
import com.charly.market.auth.model.dto.OAuthUserInfo;
import com.charly.market.auth.repository.AuthRedisRepository;
import com.charly.market.global.security.JwtProperties;
import com.charly.market.global.security.JwtTokenProvider;
import com.charly.market.global.security.JwtTokenProvider.TokenPair;
import com.charly.market.user.model.entity.User;
import com.charly.market.user.repository.UserRepository;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OAuth2ServiceUnitTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private JwtTokenProvider tokenProvider;

  @Mock
  private AuthRedisRepository redis;

  @Mock
  private JwtProperties props;

  @InjectMocks
  private OAuth2Service oAuth2Service;

  @Test
  @DisplayName("OAuth 로그인 시 신규 유저 생성 후 토큰 발급 확인")
  void testOAuthLogin_NewUser() {
    // given
    OAuthUserInfo userInfo = new OAuthUserInfo("test@kakao.com", "홍길동");
    String code = UUID.randomUUID().toString();

    when(userRepository.findByEmail(userInfo.getEmail())).thenReturn(Optional.empty());
    when(userRepository.save(any(User.class)))
        .thenAnswer(inv -> inv.getArgument(0));

    TokenPair tokenPair = new TokenPair("accessToken", "refreshToken", "accessJti123");
    when(tokenProvider.createTokens(anyString(), anyString())).thenReturn(tokenPair);
    when(props.refreshTtl()).thenReturn(Duration.ofDays(7));
    when(props.accessTtl()).thenReturn(Duration.ofHours(1));

    // when
    TokenResponse response = oAuth2Service.login("kakao", code);

    // then
    assertThat(response.accessToken()).isEqualTo("accessToken");
    verify(userRepository, times(1)).save(any(User.class));
    verify(redis, times(1)).saveRefresh(anyString(), anyString(), any());
  }
}

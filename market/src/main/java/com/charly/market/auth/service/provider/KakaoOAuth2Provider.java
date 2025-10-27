package com.charly.market.auth.service.provider;

import com.charly.market.auth.model.dto.OAuthUserInfo;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
* 카카오 로그인 이메일 못받는 이슈로 사용 중지
* */
@Component("kakao")
@RequiredArgsConstructor
public class KakaoOAuth2Provider implements OAuth2Provider {

  private final RestTemplate restTemplate;

  @Value("${oauth2.kakao.client-id}")
  private String clientId;

  @Value("${oauth2.kakao.redirect-uri}")
  private String redirectUri;

  @Value("${oauth2.kakao.client-secret}")
  private String clientSecret;

  @Override
  public OAuthUserInfo getUserInfo(String code) {
    // 1. 인가 코드 → 액세스 토큰 요청
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", clientId);
    params.add("redirect_uri", redirectUri);
    params.add("client_secret", clientSecret);
    params.add("code", code);

    System.out.println("파라미터 : " + params);
    ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(
        "https://kauth.kakao.com/oauth/token",
        new HttpEntity<>(params, createHeaders()),
        Map.class
    );
    System.out.println("redis 활용 ");
    String accessToken = (String) tokenResponse.getBody().get("access_token");
    System.out.println("엑세스 토큰 : " + accessToken);

    // 2. 사용자 정보 요청
    HttpHeaders headers = createHeaders();
    headers.add("Authorization", "Bearer " + accessToken);
    ResponseEntity<Map> userResponse = restTemplate.exchange(
        "https://kapi.kakao.com/v2/user/me",
        HttpMethod.GET,
        new HttpEntity<>(headers),
        Map.class
    );

    Map<String, Object> kakaoAccount = (Map<String, Object>) userResponse.getBody().get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

    return new OAuthUserInfo(
        (String) kakaoAccount.get("email"),
        (String) profile.get("nickname")
    );
  }

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    return headers;
  }
}

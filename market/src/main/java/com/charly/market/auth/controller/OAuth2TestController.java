package com.charly.market.auth.controller;

import com.charly.market.auth.model.dto.AuthRequest.TokenResponse;
import com.charly.market.auth.model.dto.OAuthLoginRequest;
import com.charly.market.auth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class OAuth2TestController {

  private final OAuth2Service oAuth2Service;

  /** 카카오 로그인 테스트 폼 */
  @GetMapping("/oauth2")
  public String oauth2Form() {
    return "oauth2-test";
  }

  /** 카카오 로그인 처리 (인가 코드 받아 테스트) */
  @GetMapping("/oauth2/kakao/callback")
  public String kakaoCallback(@RequestParam String code, Model model) {
    OAuthLoginRequest req = new OAuthLoginRequest(code, "test-device");

    // 실제 로그인 로직 실행
    TokenResponse tokenResponse = oAuth2Service.login("kakao", req);

    model.addAttribute("accessToken", tokenResponse.accessToken());
    model.addAttribute("refreshToken", tokenResponse.refreshToken());

    return "oauth2-result";
  }
}

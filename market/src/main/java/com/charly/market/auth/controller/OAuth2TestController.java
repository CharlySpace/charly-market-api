package com.charly.market.auth.controller;

import com.charly.market.auth.model.dto.AuthRequest.TokenResponse;
import com.charly.market.auth.model.dto.OAuthLoginRequest;
import com.charly.market.auth.service.OAuth2Service;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class OAuth2TestController {

  @Value("${oauth2.kakao.client-id}")
  private String clientId;

  @Value("${oauth2.kakao.redirect-uri}")
  private String redirectUri;

  /** 카카오 로그인 테스트 폼 */
  @GetMapping("/oauth2-test")
  public String oauth2Form() {
    return "oauth2-test";
  }

  @GetMapping("/oauth2")
  public void redirectToKakao(HttpServletResponse response) throws IOException {
    // ✅ 카카오 로그인 페이지로 리다이렉트
    String url = "https://kauth.kakao.com/oauth/authorize"
        + "?client_id=" + clientId
        + "&redirect_uri=" + redirectUri
        + "&response_type=code";
    response.sendRedirect(url);
  }
}

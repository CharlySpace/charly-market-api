//package com.charly.market.auth.service;
//
//import com.charly.market.auth.model.dto.AuthRequest.TokenResponse;
//import com.charly.market.auth.model.dto.OAuthLoginRequest;
//import com.charly.market.auth.model.dto.OAuthUserInfo;
//import com.charly.market.auth.repository.AuthRedisRepository;
//import com.charly.market.auth.service.provider.OAuth2Provider;
//import com.charly.market.global.constant.UserRole;
//import com.charly.market.global.security.JwtProperties;
//import com.charly.market.global.security.JwtTokenProvider;
//import com.charly.market.global.security.JwtTokenProvider.TokenPair;
//import com.charly.market.grade.service.util.DefaultGradeProvider;
//import com.charly.market.user.model.entity.User;
//import com.charly.market.user.repository.UserRepository;
//import java.util.Map;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class OAuth2Service {
//
//  private final Map<String, OAuth2Provider> providers;
//  private final JwtTokenProvider tokenProvider;
//  private final AuthRedisRepository redis;
//  private final UserRepository userRepository;
//  private final JwtProperties props;
//  private final DefaultGradeProvider defaultGradeProvider;
//
//  @Transactional
//  public TokenResponse login(String providerName, String code) {
//    OAuth2Provider provider = providers.get(providerName.toLowerCase());
//    if (provider == null) throw new IllegalArgumentException("UNSUPPORTED_PROVIDER");
//
//    OAuthUserInfo userInfo = provider.getUserInfo(code);
//    System.out.println("user email = " + userInfo.getEmail());
//    System.out.println("user nickname = " + userInfo.getNickname());
//
//    User user = userRepository.findByEmail(userInfo.getEmail())
//                              .orElseGet(() -> userRepository.save(
//                                  User.builder()
//                                      .username(userInfo.getEmail())
////                                      .password(passwordEncoder.encode(request.password()))
////                                      .name(request.name())
//                                      .nickname(userInfo.getNickname())
//                                      .email(userInfo.getEmail())
////                                      .phone(request.phone())
//                                      .role(UserRole.USER)
//                                      .status("Y")
//                                      .balance(0)
//                                      .tradeCount(0)
//                                      .storedPoint(0)
//                                      .grade(defaultGradeProvider.getDefaultGrade())
//                                      .build()
//                              ));
//
//    System.out.println("절차 완료");
//    TokenPair pair = tokenProvider.createTokens(user.getUsername(), user.getRole().name());
//
//    redis.saveRefresh(user.getUsername(), pair.refreshToken(), props.refreshTtl());
//
//    return new TokenResponse(
//        pair.accessToken(), props.accessTtl().toSeconds(),
//        pair.refreshToken(), props.refreshTtl().toSeconds()
//    );
//  }
//}

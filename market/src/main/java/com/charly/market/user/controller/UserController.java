package com.charly.market.user.controller;

import com.charly.market.user.model.dto.CreateUserRequest;
import com.charly.market.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
  private final UserService userService;

  @PostMapping()
  public ResponseEntity<String> signUp(@RequestBody CreateUserRequest req) {
    System.out.println(req.toString());
    userService.signUp(req);
    return ResponseEntity.ok("회원가입 성공");
  }
}

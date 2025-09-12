package com.charly.market.user.controller;

import com.charly.market.user.model.dto.CreateUserRequest;
import com.charly.market.user.model.dto.UserResponse;
import com.charly.market.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
  private final UserService userService;

  @GetMapping()
  public ResponseEntity<List<UserResponse>> findUserList() {
    List<UserResponse> userListResponseList = userService.findAll();
    return ResponseEntity.ok(userListResponseList);
  }

  @GetMapping("/{username}")
  public ResponseEntity<UserResponse> findUserById(@PathVariable String username) {
    UserResponse userResponse = userService.findByUsername(username);
    return ResponseEntity.ok(userResponse);
  }

  @PostMapping()
  public ResponseEntity<String> signUp(@RequestBody CreateUserRequest req) {
    System.out.println(req.toString());
    userService.signUp(req);
    return ResponseEntity.ok("회원가입 성공");
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<String> signOut(@PathVariable String username) {
    userService.signOut(username);
    return ResponseEntity.ok("삭제 성공");
  }
}
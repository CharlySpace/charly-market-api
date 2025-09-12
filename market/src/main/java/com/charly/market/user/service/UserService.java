package com.charly.market.user.service;

import com.charly.market.user.model.dto.CreateUserRequest;
import com.charly.market.user.model.dto.UserResponse;
import java.util.List;

public interface UserService {

  void signUp(CreateUserRequest request);
  List<UserResponse> findAll();
  UserResponse findByUsername(String username);
  void signOut(String username);
}

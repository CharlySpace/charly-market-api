package com.charly.market.user.service;

import com.charly.market.user.model.dto.CreateUserRequest;

public interface UserService {

  void signUp(CreateUserRequest request);

}

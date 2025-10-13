package com.charly.market.user.service;

import static org.junit.jupiter.api.Assertions.*;

import com.charly.market.user.model.dto.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @Test
  public void signUp() {

    for (int i = 1; i < 6; i++) {
      //given
      CreateUserRequest request = new CreateUserRequest(
          "id" + i,
          "password" + i,
          "name" + i,
          "nickname" + i,
          "email" + i + "@test.com",
          "010-" + i + "129-2034"
      );

      //when
      userService.signUp(request);
    }

    //then
    userService.findAll().forEach(System.out::println);
  }
}
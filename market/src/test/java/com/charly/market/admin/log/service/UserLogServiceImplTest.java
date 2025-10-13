package com.charly.market.admin.log.service;

import static org.junit.jupiter.api.Assertions.*;

import com.charly.market.admin.log.model.dto.UserLogSearchRequest;
import com.charly.market.user.model.dto.ChangePasswordRequest;
import com.charly.market.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserLogServiceImplTest {

  @Autowired
  private UserLogService userLogService;

  @Autowired
  private UserService userService;

  @Test
  public void createUserLog() {

    for (int i = 1; i < 6; i++) {
      // given
      ChangePasswordRequest request = new ChangePasswordRequest(
          "name" + i,
          "password" + i,
          "password" + i * i
      );
      ChangePasswordRequest request2 = new ChangePasswordRequest(
          "name" + i,
          "password" + i * i,
          "password" + i
      );

      // when
      userService.changePassword(request);
      userService.changePassword(request2);
    }

    // then
    UserLogSearchRequest request3 = new UserLogSearchRequest(
        null,
        null,
        null,
        null,
        null,
        null,
        null
    );
    System.out.println(userLogService.searchLogs(request3));
  }
}
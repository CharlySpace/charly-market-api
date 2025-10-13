package com.charly.market.account.service;

import com.charly.market.account.model.dto.AccountRequest;
import com.charly.market.account.model.dto.AccountResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceImplTest {

  @Autowired
  private AccountService accountService;

  @Test
  public void createAccount() {
    for (long i = 1; i < 6; i++) {
      String bankName = "농협";
      String accountNumber = "123-455324-32" + i;
      String bankOwner = "김";
      AccountRequest request = new AccountRequest(
          bankName, accountNumber, bankOwner, i
      );
      accountService.create(request);
    }
    List<AccountResponse> response = accountService.findAll();
    System.out.println(response);
  }
}
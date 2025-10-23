package com.charly.market.account.service;

import com.charly.market.account.model.dto.AccountRequest;
import com.charly.market.account.model.dto.AccountResponse;
import com.charly.market.account.model.dto.AccountSearchRequest;
import com.charly.market.account.model.dto.AccountUpdateRequest;
import java.util.List;
import org.springframework.data.domain.Page;

public interface AccountService {

  void create(AccountRequest account);

  List<AccountResponse> findAll();

  Page<AccountResponse> search(AccountSearchRequest request);

  void changeContent(Long accountId, AccountUpdateRequest account);
}

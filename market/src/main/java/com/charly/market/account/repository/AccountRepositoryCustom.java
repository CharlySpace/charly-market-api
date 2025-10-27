package com.charly.market.account.repository;

import com.charly.market.account.model.dto.AccountSearchRequest;
import com.charly.market.account.model.entity.Account;
import org.springframework.data.domain.Page;

public interface AccountRepositoryCustom {

  Page<Account> search(AccountSearchRequest request);
}

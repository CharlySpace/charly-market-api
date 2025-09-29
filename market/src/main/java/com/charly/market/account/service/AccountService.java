package com.charly.market.account.service;

import com.charly.market.account.model.dto.AccountRequest;
import com.charly.market.account.model.dto.AccountResponse;
import com.charly.market.account.model.dto.AccountUpdateRequest;
import com.charly.market.account.model.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    void create(AccountRequest account);
    List<AccountResponse> findAll();
    void changeContent(Long accountId,AccountUpdateRequest account);
}

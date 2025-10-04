package com.charly.market.account.service.utill;

import com.charly.market.account.model.entity.Account;
import com.charly.market.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFinder {
    private final AccountRepository accountRepository;
    public Account getById(Long id){//나중에 토큰값으로 찾아줘야함?
        return accountRepository.findById(id).orElse(null);
    }
}

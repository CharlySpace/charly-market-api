package com.charly.market.account.service;

import com.charly.market.account.model.dto.AccountRequest;
import com.charly.market.account.model.dto.AccountResponse;
import com.charly.market.account.model.dto.AccountUpdateRequest;
import com.charly.market.account.model.entity.Account;
import com.charly.market.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public void create(AccountRequest request) {
        Account account=Account.builder()
                .bankName(request.bankName())
                .accountNumber(request.accountNumber())
                .bankOwner(request.bankOwner())
                .build();
        accountRepository.save(account);
    }

    @Override
    public List<AccountResponse> findAll() {
        List<Account>  accountList = accountRepository.findAll();
        List<AccountResponse> accountResponseList = new ArrayList<>();
        for(Account account: accountList){
           AccountResponse accountResponse=new AccountResponse(
                   account.getBankName(),
                   account.getAccountNumber(),
                   account.getBankOwner(),
                   account.getUserId()
           );
           accountResponseList.add(accountResponse);
        }
        return accountResponseList;
    }

    @Override
    public void changeContent(Long accountId,AccountUpdateRequest request) {
       Account account= accountRepository.findById(accountId).orElseThrow();
       account.changeAccountContent(request);
    }

}

package com.charly.market.account.controller;


import com.charly.market.account.model.dto.AccountRequest;
import com.charly.market.account.model.dto.AccountResponse;
import com.charly.market.account.model.dto.AccountUpdateRequest;
import com.charly.market.account.model.entity.Account;
import com.charly.market.account.repository.AccountRepository;
import com.charly.market.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    @GetMapping()
    public ResponseEntity<List<AccountResponse>> findAccount(){
        List<AccountResponse> accountList=accountService.findAll();
        return ResponseEntity.ok(accountList);
    }
    @PostMapping()
    public ResponseEntity<String> createAccount(@RequestBody AccountRequest account){
        accountService.create(account);
        return ResponseEntity.ok("생성");
    }
    @PatchMapping("/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable("accountId") Long accountId, @RequestBody AccountUpdateRequest account){
        accountService.changeContent(accountId,account);
        return ResponseEntity.ok("변경 성공?");
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        accountRepository.deleteById(accountId);

        return  ResponseEntity.ok("삭제 성공");
    }
}

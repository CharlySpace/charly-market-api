package com.charly.market.account.controller;


import com.charly.market.account.model.dto.AccountRequest;
import com.charly.market.account.model.dto.AccountResponse;
import com.charly.market.account.model.dto.AccountSearchRequest;
import com.charly.market.account.model.dto.AccountUpdateRequest;
import com.charly.market.account.repository.AccountRepository;
import com.charly.market.account.service.AccountService;
import com.charly.market.admin.log.model.dto.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

  private final AccountRepository accountRepository;
  private final AccountService accountService;

  @GetMapping()
  public ResponseEntity<List<AccountResponse>> findAccount() {
    List<AccountResponse> accountList = accountService.findAll();
    return ResponseEntity.ok(accountList);
  }

  @GetMapping("/search")
  public PageResponse<AccountResponse> search(AccountSearchRequest request) {
    return PageResponse.of(accountService.search(request));
  }


  @PostMapping()
  public ResponseEntity<String> createAccount(@RequestBody AccountRequest account) {
    accountService.create(account);
    return ResponseEntity.ok("생성");
  }

  @PatchMapping("/{accountId}")
  public ResponseEntity<String> updateAccount(@PathVariable("accountId") Long accountId,
      @RequestBody AccountUpdateRequest account) {
    accountService.changeContent(accountId, account);
    return ResponseEntity.ok("변경 성공?");
  }

  @DeleteMapping("/{accountId}")
  public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
    accountRepository.deleteById(accountId);

    return ResponseEntity.ok("삭제 성공");
  }
}

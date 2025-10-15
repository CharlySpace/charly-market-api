package com.charly.market.account.repository;

import com.charly.market.account.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

}

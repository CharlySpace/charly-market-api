package com.charly.market.account.repository;

import com.charly.market.account.model.dto.AccountSearchRequest;
import com.charly.market.account.model.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

  @PersistenceContext
  EntityManager em;

  @Override
  public Page<Account> search(AccountSearchRequest request) {
    String baseQuery = "SELECT a FROM Account a WHERE 1=1";
    String countQuery = "SELECT COUNT(a) FROM Account a WHERE 1=1";

    StringBuilder filter = new StringBuilder();

    if (request.bankName() != null && !request.bankName().isBlank()) {
      filter.append(" AND a.bankName Like :bankName");
    }
    if (request.userId() != null) {
      filter.append(" AND a.user.id = :userId");
    }

    String jpql = baseQuery + filter + " ORDER BY " + request.sortBy() + " " + request.direction();
    var query = em.createQuery(jpql, Account.class);

    if (request.bankName() != null && !request.bankName().isBlank()) {
      query.setParameter("bankName", "%" + request.bankName() + "%");
    }
    if (request.userId() != null) {
      query.setParameter("userId", request.userId());
    }

    query.setFirstResult(request.page() * request.size());
    query.setMaxResults(request.size());
    List<Account> results = query.getResultList();

    var countQ = em.createQuery(countQuery + filter, Long.class);
    if (request.bankName() != null && !request.bankName().isBlank()) {
      countQ.setParameter("bankName", "%" + request.bankName() + "%");
    }
    if (request.userId() != null) {
      countQ.setParameter("userId", request.userId());
    }
    long total = countQ.getSingleResult();

    return new PageImpl<>(results, PageRequest.of(request.page(), request.size()), total);
  }
}

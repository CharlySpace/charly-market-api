package com.charly.market.admin.log.repository;

import com.charly.market.admin.log.model.dto.UserLogSearchRequest;
import com.charly.market.admin.log.model.entity.UserLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class UserLogRepositoryImpl implements UserLogRepositoryCustom {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Page<UserLog> search(UserLogSearchRequest request) {
    String baseQuery = "SELECT u FROM UserLog u WHERE 1=1";
    String countQuery = "SELECT COUNT(u) FROM UserLog u WHERE 1=1";

    StringBuilder filter = new StringBuilder();

    if (request.userId() != null) {
      filter.append(" AND u.user.id = :userId");
    }
    if (request.keyword() != null && !request.keyword().isBlank()) {
      filter.append(" AND u.logContent LIKE :keyword");
    }
    if (request.columnName() != null && !request.columnName().isBlank()) {
      filter.append(" AND u.columnName = :columnName");
    }

    String jpql = baseQuery + filter + " ORDER BY u." + request.sortBy() + " " + request.direction();
    var query = em.createQuery(jpql, UserLog.class);

    if (request.userId() != null) query.setParameter("userId", request.userId());
    if (request.keyword() != null && !request.keyword().isBlank())
      query.setParameter("keyword", "%" + request.keyword() + "%");
    if (request.columnName() != null && !request.columnName().isBlank())
      query.setParameter("columnName", request.columnName());

    query.setFirstResult(request.page() * request.size());
    query.setMaxResults(request.size());
    List<UserLog> results = query.getResultList();

    var countQ = em.createQuery(countQuery + filter, Long.class);
    if (request.userId() != null) countQ.setParameter("userId", request.userId());
    if (request.keyword() != null && !request.keyword().isBlank())
      countQ.setParameter("keyword", "%" + request.keyword() + "%");
    if (request.columnName() != null && !request.columnName().isBlank())
      countQ.setParameter("columnName", request.columnName());

    long total = countQ.getSingleResult();

    return new PageImpl<>(results, PageRequest.of(request.page(), request.size()), total);
  }
}

package com.charly.market.global.repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

// 공통 페이징 작업 클래스
public abstract class BasePagedRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    protected Page<T> doSearch(String baseQuery, String countQuery,
                             StringBuilder filter, Map<String, Object> params,
                             int page, int size, Class<T> type) {
        var jpql = baseQuery + filter;
        var query = em.createQuery(jpql, type);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            query.setParameter(key, value);
        }

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        var results = query.getResultList();

        var countQ = em.createQuery(countQuery + filter, Long.class);
        params.forEach(countQ::setParameter);
        var total = countQ.getSingleResult();

        return new PageImpl<>(results, PageRequest.of(page, size), total);
    }
}

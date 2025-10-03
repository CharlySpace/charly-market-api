package com.charly.market.review.repository;

import com.charly.market.admin.log.model.entity.UserLog;
import com.charly.market.global.repository.BasePagedRepository;
import com.charly.market.review.model.Review;
import com.charly.market.review.model.dto.ReviewSearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewRepositoryCustomImpl extends BasePagedRepository<Review>
        implements ReviewRepositoryCustom {


    @Override
    public Page<Review> search(ReviewSearchRequest request) {
        // 기본 페이징 넘길 정보
        String baseQuery = "SELECT r FROM Review r WHERE 1=1";
        String countQuery = "SELECT COUNT(r) FROM Review r WHERE 1=1";


        // 여기 부터는 각 엔티티 필요 검색 조건
        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (request.reviewerId() != null) {
            filter.append(" AND r.reviewerId.id = :reviewerId");
            params.put("reviewerId", request.reviewerId());
        }
        if (request.revieweeId() != null) {
            filter.append(" AND r.revieweeId.id = :revieweeId");
            params.put("revieweeId", request.revieweeId());
        }
        if (request.reviewStar() != null) {
            filter.append(" AND r.reviewStar >= :reviewStar");
            params.put("reviewStar", request.reviewStar());
        }
        if (request.keyword() != null && !request.keyword().isBlank()) {
            filter.append(" AND r.content LIKE :keyword");
            params.put("keyword", "%" + request.keyword() + "%");
        }

        filter.append(" ORDER BY r." + request.sortBy() + " " + request.direction());


        return doSearch(baseQuery, countQuery, filter, params,
                request.page(), request.size(), Review.class);
    }

}



package com.charly.market.inquiry.repository;

import com.charly.market.global.repository.BasePagedRepository;
import com.charly.market.inquiry.model.dto.InquirySearchRequest;
import com.charly.market.inquiry.model.entity.Inquiry;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class InquiryRepositoryCustomImpl extends BasePagedRepository<Inquiry> implements InquiryRepositoryCustom {


    @Override
    public Page<Inquiry> search(InquirySearchRequest request) {

        // 기본 페이징 넘길 정보

        String baseQuery = "SELECT n From Inquiry n WHERE 1=1";
        String countQuery = "SELECT COUNT(n) From Inquiry n WHERE 1=1";

        // 각 엔티티 필요 검색 조건
        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        // 상태
        if (request.status() != null && !request.status().isBlank()) {
            filter.append(" AND n.status = :status ");
            params.put("status", request.status());
        }

        // 작성자
        if (request.userId() != null) {
            filter.append(" AND n.user.id = :userId ");
            params.put("userId", request.userId());
        }

        // 관리자
        if (request.adminUserId() != null) {
            filter.append(" AND n.adminUser.id = :adminUserId ");
            params.put("adminUserId", request.adminUserId());
        }

        // 키워드 (제목/내용)
        if (request.titleKeyword() != null && !request.titleKeyword().isBlank()) {
            filter.append(" AND (LOWER(n.title) LIKE :kw OR LOWER(n.content) LIKE :kw) ");
            params.put("kw", "%" + request.titleKeyword().toLowerCase() + "%");
        }

       // answer("Y" → 답변 있음 / "N" → 없음)
        if (request.answer() != null && !request.answer().isBlank()) {
            String answer = request.answer().trim().toUpperCase();
            switch (answer) {
                case "Y" -> filter.append(" AND n.answer IS NOT NULL AND TRIM(n.answer) <> '' ");
                case "N" -> filter.append(" AND (n.answer IS NULL OR TRIM(n.answer) = '') ");
                default -> {
                    // "ALL" 또는 그 외 값은 필터 미적용
                }
            }
        }


        // 정렬 조건
        filter.append(" ORDER BY n.")
                .append(request.sortBy())
                .append(" ")
                .append(request.direction().name());


        return doSearch(
                baseQuery,
                countQuery,
                filter,
                params,
                request.page(),
                request.size(),
                Inquiry.class
        );

    }
}


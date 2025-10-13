package com.charly.market.notice.repository;

import com.charly.market.global.repository.BasePagedRepository;
import com.charly.market.notice.model.dto.NoticeSearchRequest;
import com.charly.market.notice.model.entity.Notice;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class NoticeRepositoryCustomImpl extends BasePagedRepository<Notice> implements NoticeRepositoryCustom {

    @Override
    public Page<Notice> search(NoticeSearchRequest request) {
        // 기본 페이징 넘길 정보
        String baseQuery = "SELECT n FROM Notice n WHERE 1=1";
        String countQuery = "SELECT COUNT(n) FROM Notice n WHERE 1=1";

        // 여기 부터는 각 엔티티 필요 검색 조건
        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        // 상태 필터 (Y/N)
        if (request.status() != null && !request.status().isBlank()) {
            filter.append(" AND n.status = :status");
            params.put("status", request.status());
        }

        // 제목 검색 (LIKE)
        if (request.titleKeyword() != null && !request.titleKeyword().isBlank()) {
            filter.append(" AND n.title LIKE :titleKeyword");
            params.put("titleKeyword", "%" + request.titleKeyword() + "%");
        }

        // 정렬 조건
        filter.append(" ORDER BY n.")
                .append(request.sortBy())
                .append(" ")
                .append(request.direction().name());

        // BasePagedRepository에 정의된 페이징 검색 메서드 호출
        return doSearch(
                baseQuery,
                countQuery,
                filter,
                params,
                request.page(),
                request.size(),
                Notice.class
        );
    }
}

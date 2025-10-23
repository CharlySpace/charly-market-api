package com.charly.market.alarmbox.repository;

import com.charly.market.alarmbox.model.entity.AlarmBox;
import com.charly.market.alarmbox.model.dto.AlarmBoxSearchRequest;
import com.charly.market.global.repository.BasePagedRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AlarmBoxRepositoryCustomImpl
        extends BasePagedRepository<AlarmBox>
        implements AlarmBoxRepositoryCustom {

    @Override
    public Page<AlarmBox> search(AlarmBoxSearchRequest request) {

        // 기본 쿼리 (엔티티 기준)
        String baseQuery  = "SELECT a FROM AlarmBox a WHERE 1=1";
        String countQuery = "SELECT COUNT(a) FROM AlarmBox a WHERE 1=1";

        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        // 필수: userId
        if (request.userId() == null) {
            throw new IllegalArgumentException("userId is required");
        }
        filter.append(" AND a.user.id = :userId");
        params.put("userId", request.userId());

        // 선택: auctionId
        if (request.auctionId() != null) {
            filter.append(" AND a.auctionItem.id = :auctionId");
            params.put("auctionId", request.auctionId());
        }

        // 선택: status (Y/N)
        if (request.status() != null && !request.status().isBlank()) {
            filter.append(" AND a.status = :status");
            params.put("status", request.status());
        }

        // 선택: content LIKE
        if (request.content() != null && !request.content().isBlank()) {
            filter.append(" AND a.content LIKE :content");
            params.put("content", "%" + request.content().trim() + "%");
        }

        // 정렬 (요청값 사용, tie-breaker로 id DESC 추가해서 안정성 확보)
        filter.append(" ORDER BY a.")
                .append(request.sortBy())
                .append(" ")
                .append(request.direction().name())
                .append(", a.id DESC");

        // 공통 페이징 실행
        return doSearch(
                baseQuery, countQuery, filter, params,
                request.page(), request.size(), AlarmBox.class
        );
    }
}

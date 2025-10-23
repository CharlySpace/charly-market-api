package com.charly.market.report.repository;

import com.charly.market.global.repository.BasePagedRepository;
import com.charly.market.report.model.dto.ReportSearchRequest;
import com.charly.market.report.model.entity.Report;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class ReportRepositoryCustomImpl extends BasePagedRepository implements ReportRepositoryCustom {


    @Override
    public Page<Report> search(ReportSearchRequest request) {

        // 기본 페이징 넘길 정보

        String baseQuery = "SELECT n From Report n WHERE 1=1";
        String countQuery = "SELECT COUNT(n) From Report n WHERE 1=1";

        // 여기 부터는 각 엔티티 필요 검색 조건
        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        // 상태 필터 (Y/N)
        if (request.status() != null && !request.status().isBlank()) {
            filter.append(" AND n.status = :status ");
            params.put("status", request.status());
        }

        // 카테고리 ID로 필터링
        if (request.categoryId() != null) {
            filter.append(" AND n.category_id = :categoryId ");
            params.put("categoryId", request.categoryId());
        }



        // 경매글 제목 키워드(AuctionItem.title Like)
        if (request.auctionTitleKeyword() != null && !request.auctionTitleKeyword().isBlank()) {
            filter.append(" AND n.title LIKE :titleKeyword ");
            params.put("titleKeyword", request.auctionTitleKeyword() + "%");
        }

        // 신고 내용(content) 키워드 검색
        // toLowerCase : Like 검색을 했을때 대소문자를 구분하여 검색이 될 수 있는데
        // 이걸 사용하면 사용자 입력과 DB 값 모두 소문자로 통잃래서 대소문자 무시 검색이 가능하다.
        if (request.contentKeyword() != null && !request.contentKeyword().isBlank()) {
            filter.append(" AND n.content LIKE :contentKeyword ");
            params.put("contentKeyword", request.contentKeyword().toLowerCase() + "%");
        }

        // 신고자 ID 검색
        if (request.reportUserId() != null) {
            filter.append(" AND n.reportUser.id = :reportUserId ");
            params.put("reportUserId", request.reportUserId());
        }

        // 처리자 검색
        if(request.adminUserId() != null) {
            filter.append(" AND n.reportAdmin.id = :adminUserId ");
            params.put("adminUserId", request.adminUserId());
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
                Report.class
        );
    }
}

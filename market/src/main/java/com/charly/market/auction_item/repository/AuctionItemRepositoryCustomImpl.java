package com.charly.market.auction_item.repository;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.AuctionItemSearchRequest;
import com.charly.market.global.repository.BasePagedRepository;
import com.charly.market.review.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuctionItemRepositoryCustomImpl extends BasePagedRepository<AuctionItem> implements AuctionItemRepositoryCustom{
    @Override
    public Page<AuctionItem> search(AuctionItemSearchRequest request) {
        // 기본 페이징 넘길 정보
        String baseQuery = "SELECT a FROM AuctionItem a WHERE 1=1";
        String countQuery = "SELECT COUNT(a) FROM AuctionItem a WHERE 1=1";


        // 여기 부터는 각 엔티티 필요 검색 조건
        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (request.userId() != null) {
            filter.append(" AND a.userId.id = :userId");
            params.put("userId", request.userId());
        }
        if (request.categoryName() != null) {
            filter.append(" AND a.categoryId.categoryName Like :categoryName");
            params.put("categoryName", "%" + request.categoryName() + "%");
        }
        if (request.currentPrice() != null) {
            filter.append(" AND a.currentPrice >= :currentPrice");
            params.put("currentPrice", request.currentPrice());
        }

        if (request.keyword() != null && !request.keyword().isBlank()) {
            filter.append(" AND a.title LIKE :keyword");
            params.put("keyword", "%" + request.keyword() + "%");
        }

        filter.append(" ORDER BY a." + request.sortBy() + " " + request.direction());


        return doSearch(baseQuery, countQuery, filter, params,
                request.page(), request.size(), AuctionItem.class);
    }

}

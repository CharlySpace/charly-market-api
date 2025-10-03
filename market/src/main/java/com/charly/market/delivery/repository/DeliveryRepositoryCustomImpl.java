package com.charly.market.delivery.repository;

import com.charly.market.delivery.model.Delivery;
import com.charly.market.delivery.model.dto.DeliverySearchRequest;
import com.charly.market.global.repository.BasePagedRepository;
import com.charly.market.review.model.Review;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;


public class DeliveryRepositoryCustomImpl extends BasePagedRepository<Delivery> implements DeliveryRepositoryCustom {
    @Override
    public Page<Delivery> search(DeliverySearchRequest request) {
        // 기본 페이징 넘길 정보
        String baseQuery = "SELECT d FROM Delivery d WHERE 1=1";
        String countQuery = "SELECT COUNT(d) FROM Delivery d WHERE 1=1";


        // 여기 부터는 각 엔티티 필요 검색 조건
        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (request.sendId() != null) {
            filter.append(" AND d.sendId.id = :sendId");
            params.put("sendId", request.sendId());
        }
        if (request.receiverId() != null) {
            filter.append(" AND d.receiverId.id = :receiverId");
            params.put("receiverId", request.receiverId());
        }
        if (request.deliveryStatus() != null) {
            filter.append(" AND d.deliveryStatus LIKE :deliveryStatus");
            params.put("deliveryStatus", "%" + request.deliveryStatus() + "%");

        }

        filter.append(" ORDER BY d." + request.sortBy() + " " + request.direction());


        return doSearch(baseQuery, countQuery, filter, params,
                request.page(), request.size(), Delivery.class);

    }
}

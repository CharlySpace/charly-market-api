package com.charly.market.auction_bid.repository;

import com.charly.market.auction_bid.model.AuctionBid;
import com.charly.market.auction_bid.model.dto.BidSearchRequest;
import com.charly.market.global.repository.BasePagedRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.Map;

@Repository
public class AuctionBidRepositoryCustomImpl extends BasePagedRepository<AuctionBid> implements AuctionBidRepositoryCustom {
    @Override
    public Page<AuctionBid> search(BidSearchRequest request) {
        // 기본 페이징 넘길 정보
        String baseQuery = "SELECT a FROM AuctionBid a WHERE 1=1";
        String countQuery = "SELECT COUNT(a) FROM AuctionBid a WHERE 1=1";


        // 여기 부터는 각 엔티티 필요 검색 조건
        StringBuilder filter = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (request.seller() != null) {
            filter.append(" AND a.bidUser.id = :bidUser");
            params.put("bidUser", request.seller());
        }
        if (request.auction() != null) {
            filter.append(" AND a.auction.id = :auction");
            params.put("auction", request.auction());
        }
        if (request.bidAmount() != null) {
            filter.append(" AND a.bidAmount >= :bidAmount");
            params.put("bidAmount", request.bidAmount());
        }
        if (request.successStatus() != null && !request.successStatus().isBlank()) {
            filter.append(" AND a.successStatus = :successStatus");
            params.put("successStatus",  request.successStatus() );
        }

        filter.append(" ORDER BY a." + request.sortBy() + " " + request.direction());


        return doSearch(baseQuery, countQuery, filter, params,
                request.page(), request.size(), AuctionBid.class);
    }
}

package com.charly.market.point.model.dto;

import org.springframework.data.domain.Sort;

public record PointLogSearchRequest(
        Long userId,//자동으로 유저 아이디 받아와야됨?
        String type,
        Long bidId,// 엔티티에 AuctionBid타입으로 저장되어 있어서 생각 해볼 필요 있을 듯
        String keyword,
        Integer size,
        Integer page,
        Sort.Direction direction,
        String sortBy

) {
    public PointLogSearchRequest{
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 5;
        if (direction == null) direction = Sort.Direction.DESC;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
    }
}

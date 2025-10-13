package com.charly.market.notice.repository;

import com.charly.market.auction_bid.model.dto.BidSearchRequest;
import com.charly.market.notice.model.dto.NoticeSearchRequest;
import com.charly.market.notice.model.entity.Notice;
import org.springframework.data.domain.Page;

public interface NoticeRepositoryCustom {
    Page<Notice> search (NoticeSearchRequest request);
}

package com.charly.market.review.repository;

import com.charly.market.admin.log.model.dto.UserLogSearchRequest;
import com.charly.market.admin.log.model.entity.UserLog;
import com.charly.market.review.model.Review;
import com.charly.market.review.model.dto.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewRepositoryCustom {
    Page<Review> search(ReviewSearchRequest request);
}

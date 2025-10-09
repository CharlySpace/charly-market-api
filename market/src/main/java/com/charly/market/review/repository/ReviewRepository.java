package com.charly.market.review.repository;

import com.charly.market.review.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> , ReviewRepositoryCustom {
//    Page<Review> findAll(Pageable pageable);
}

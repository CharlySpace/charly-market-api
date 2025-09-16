package com.charly.market.notice.repository;

import com.charly.market.notice.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    Optional<Notice> findById(Long id);
}

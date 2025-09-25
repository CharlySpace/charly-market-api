package com.charly.market.admin.log.repository;

import com.charly.market.admin.log.model.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long>, UserLogRepositoryCustom {

}

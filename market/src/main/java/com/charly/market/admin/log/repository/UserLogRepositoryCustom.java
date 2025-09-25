package com.charly.market.admin.log.repository;

import com.charly.market.admin.log.model.dto.UserLogSearchRequest;
import com.charly.market.admin.log.model.entity.UserLog;
import org.springframework.data.domain.Page;

public interface UserLogRepositoryCustom {
  Page<UserLog> search(UserLogSearchRequest request);
}

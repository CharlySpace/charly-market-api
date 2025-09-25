package com.charly.market.admin.log.service;

import com.charly.market.admin.log.model.dto.UserLogResponse;
import com.charly.market.admin.log.model.dto.UserLogSearchRequest;
import org.springframework.data.domain.Page;

public interface UserLogService {
  Page<UserLogResponse> searchLogs(UserLogSearchRequest request);
}

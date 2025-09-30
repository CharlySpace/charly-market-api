package com.charly.market.admin.log.service;

import com.charly.market.admin.log.model.dto.UserLogResponse;
import com.charly.market.admin.log.model.dto.UserLogSearchRequest;
import com.charly.market.admin.log.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLogServiceImpl implements UserLogService {

  private final UserLogRepository userLogRepository;

  @Override
  public Page<UserLogResponse> searchLogs(UserLogSearchRequest request) {
    return userLogRepository.search(request)
                            .map(log -> new UserLogResponse(
                                log.getId(),
                                log.getUser().getId(),
                                log.getLogContent(),
                                log.getColumnName()
                            ));
  }
}

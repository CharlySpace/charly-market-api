package com.charly.market.admin.log.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.admin.log.model.dto.UserLogResponse;
import com.charly.market.admin.log.model.dto.UserLogSearchRequest;
import com.charly.market.admin.log.model.entity.UserLog;
import com.charly.market.admin.log.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/user")
public class UserLogController {

  private final UserLogService userLogService;

  @GetMapping("/log")
  public PageResponse<UserLogResponse> getUserLog(UserLogSearchRequest request) {
    return PageResponse.of(userLogService.searchLogs(request));
  }

}

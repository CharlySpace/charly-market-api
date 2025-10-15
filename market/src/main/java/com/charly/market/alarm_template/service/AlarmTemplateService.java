package com.charly.market.alarm_template.service;

import com.charly.market.alarm_template.model.dto.AlarmTemplateChangeContentRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateResponse;
import com.charly.market.alarm_template.model.dto.CreateAlarmTemplateRequest;

import java.util.List;

public interface AlarmTemplateService {
    // 생성
    void createAlarmTemplate(CreateAlarmTemplateRequest request);

    // 조회
    List<AlarmTemplateResponse> findAll();

    // 검색
    AlarmTemplateResponse findByAlarmTemplateId(Long AlarmTemplateId);

    // 삭제
    void delete(Long AlarmTemplateId);

    // 수정
    void changeContent(AlarmTemplateChangeContentRequest req);
}
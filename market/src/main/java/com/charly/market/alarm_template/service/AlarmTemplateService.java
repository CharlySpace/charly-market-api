package com.charly.market.alarm_template.service;

import com.charly.market.alarm_template.model.dto.AlarmTemplateChangeContentRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateResponse;
import com.charly.market.alarm_template.model.entity.AlarmTemplate;
import com.charly.market.alarmbox.model.dto.AlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.alarmbox.model.entity.AlarmBox;

import java.util.List;
import java.util.Optional;

public interface AlarmTemplateService {
    // 생성
    void createAlarmTemplate(AlarmTemplateRequest request);

    // 조회
    List<AlarmTemplateResponse> findAll();

    // 검색
    AlarmTemplateResponse findByAlarmTemplateId(Long AlarmTemplateId);

    // 삭제
    void delete(Long AlarmTemplateId);

    // 수정
    void changeContent(AlarmTemplateChangeContentRequest req);
}
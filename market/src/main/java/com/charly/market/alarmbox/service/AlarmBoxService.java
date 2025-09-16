package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;

import java.util.List;

public interface AlarmBoxService {

    void CreateAlarmBox(AlarmBoxRequest request);
    List<AlarmBoxResponse> findAll();
    AlarmBoxResponse findByAlarmBoxId(Long AlarmBoxId);
    void delete(Long AlarmBoxId);

    
}

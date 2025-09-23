package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.alarmbox.model.entity.AlarmBox;

import java.util.List;
import java.util.Optional;

public interface AlarmBoxService {

    void createAlarmBox(AlarmBoxRequest request);
    List<AlarmBoxResponse> findAll();
    AlarmBoxResponse findByAlarmBoxId(Long AlarmBoxId);
    void delete(Long AlarmBoxId);
    Optional<AlarmBox> findById(Long id);

    void changeStatus(ChangeStatusRequest req);
}

package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxDtos;
import com.charly.market.alarmbox.model.entity.AlarmBox;
import com.charly.market.alarmbox.repository.AlarmBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmBoxImpl implements  AlarmBoxService {

    private final AlarmBoxRepository alarmBoxRepository;

    @Override
    public void CreateAlarmBox(AlarmBoxDtos request) {
        AlarmBox alarmbox = AlarmBox.builder()
                .alarm_box_id(request.alarm_box_id())
                .alarm_check(request.alarm_check())
                .alarm_content(request.alarm_content())
                .alarmId(3)
                .userId(3)
                .build();

                alarmBoxRepository.save(alarmbox);
    }
}

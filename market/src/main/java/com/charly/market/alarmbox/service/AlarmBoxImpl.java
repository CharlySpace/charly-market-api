package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.entity.AlarmBox;
import com.charly.market.alarmbox.repository.AlarmBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmBoxImpl implements  AlarmBoxService {

    private final AlarmBoxRepository alarmBoxRepository;

    @Override
    public void CreateAlarmBox(AlarmBoxRequest request) {
        AlarmBox alarmbox = AlarmBox.builder()
                .alarmCheck(request.alarm_check())
                .alarmContent(request.alarm_content())
                .build();

                alarmBoxRepository.save(alarmbox);
    }

    @Override
        public List<AlarmBoxResponse> findAll(){
       List<AlarmBox> alarmBoxList = alarmBoxRepository.findAll();
        List<AlarmBoxResponse> alarmBoxListResponses = new ArrayList<>();
        for (AlarmBox alarmBox : alarmBoxList){
            AlarmBoxResponse alarmBoxListResponse = new AlarmBoxResponse(
                    alarmBox.getAlarmCheck(),
                    alarmBox.getAlarmContent()
            );
            alarmBoxListResponses.add(alarmBoxListResponse);
    }
        return alarmBoxListResponses;
        }

    @Override
    public AlarmBoxResponse findByAlarmBoxId(Long AlarmBoxId) {
        AlarmBox alarmBox = alarmBoxRepository.findByAlarmBoxId(AlarmBoxId);
        return new AlarmBoxResponse(
                alarmBox.getAlarmCheck(),
                alarmBox.getAlarmContent()
        );
    }

    @Transactional
    @Override
    public void delete(Long AlarmBoxId) {
        AlarmBox alarmBox = alarmBoxRepository.findByAlarmBoxId(AlarmBoxId);
        alarmBox.deactivatedAlarmStatus();
    }
}

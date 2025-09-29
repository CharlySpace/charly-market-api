package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.alarmbox.model.entity.AlarmBox;

import java.util.List;
import java.util.Optional;

public interface AlarmBoxService {


    //생성
    void createAlarmBox(AlarmBoxRequest request);

    //전체 리스트 조회
    List<AlarmBoxResponse> findAll();

    // 검색 (PK 값으로 조회)
    AlarmBoxResponse findByAlarmBoxId(Long AlarmBoxId);
    //Optional<AlarmBox> findById(Long id);


    // 삭제
    void delete(Long AlarmBoxId);

    // 수정
    void changeStatus(ChangeStatusRequest req);


}

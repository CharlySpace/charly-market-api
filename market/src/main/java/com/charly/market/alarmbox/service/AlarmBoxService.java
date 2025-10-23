package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxSearchRequest;
import com.charly.market.alarmbox.model.dto.CreateAlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlarmBoxService {


    //등록
    void createAlarmBox(CreateAlarmBoxRequest request);

    //전체 리스트 조회
    List<AlarmBoxResponse> findAll();

    // 검색 (PK 값으로 조회)
    AlarmBoxResponse findByAlarmBoxId(Long AlarmBoxId);
    //Optional<AlarmBox> findById(Long id);


    // 삭제
    void delete(Long AlarmBoxId);

    // 수정
    void changeStatus(ChangeStatusRequest req);

    // 페이지 등록
    Page<AlarmBoxResponse> alarmBoxSearch(AlarmBoxSearchRequest request);

}

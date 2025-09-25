package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.alarmbox.model.entity.AlarmBox;
import com.charly.market.alarmbox.repository.AlarmBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmBoxServiceImpl implements  AlarmBoxService {

    private final AlarmBoxRepository alarmBoxRepository;

    //생성
    @Override
    public void createAlarmBox(AlarmBoxRequest request) {
        AlarmBox alarmbox = AlarmBox.builder()
                .status(request.status())
                .content(request.content())
                .build();
        System.out.println(alarmbox);
                alarmBoxRepository.save(alarmbox);
    }

    // 조회
    @Override
        public List<AlarmBoxResponse> findAll(){
       List<AlarmBox> alarmBoxList = alarmBoxRepository.findAll();
        List<AlarmBoxResponse> alarmBoxListResponses = new ArrayList<>();
        for (AlarmBox alarmBox : alarmBoxList){
            AlarmBoxResponse alarmBoxListResponse = new AlarmBoxResponse(
                    alarmBox.getStatus(),
                    alarmBox.getContent()
            );
            alarmBoxListResponses.add(alarmBoxListResponse);
    }
        return alarmBoxListResponses;
        }

        // 검색
    @Override
    public AlarmBoxResponse findByAlarmBoxId(Long id) {
        Optional<AlarmBox> alarmBox = alarmBoxRepository.findById(id);
        return alarmBox.map( a -> new AlarmBoxResponse (
                a.getStatus(),
                a.getContent()

                )).orElse(null);
    }

    // 삭제
    @Transactional
    @Override
    public void delete(Long id) {
        AlarmBox alarmBox = alarmBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AlarmBox not found: " + id));
        alarmBox.deactivatedAlarmStatus();
    }


    // 수정
    @Transactional
    @Override
    public void changeStatus(ChangeStatusRequest req) {
        AlarmBox alarmBox = alarmBoxRepository.findById(req.id()).orElseThrow();
        alarmBox.changeAlarmboxStatus(req.status());
    }

//    @Override
//    public void changeAlarmBoxStatus(ChangeAlarmBoxStatus req) {
//
//        Id id = alarmBoxRepository.findById(id)
//                .orElseThrow(()
//    }
}

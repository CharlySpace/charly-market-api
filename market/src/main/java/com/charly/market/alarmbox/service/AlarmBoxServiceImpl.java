package com.charly.market.alarmbox.service;

import com.charly.market.alarmbox.model.dto.AlarmBoxSearchRequest;
import com.charly.market.alarmbox.model.dto.CreateAlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.alarmbox.model.entity.AlarmBox;
import com.charly.market.alarmbox.repository.AlarmBoxRepository;
import com.charly.market.alarm_template.service.util.AlarmTemplateFinder;
import com.charly.market.auction_item.service.util.AuctionItemFinder;
import com.charly.market.user.service.util.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final UserFinder userFinder;
    private final AuctionItemFinder auctionItemFinder;
    private final AlarmTemplateFinder alarmTemplateFinder;

    //생성
    @Override
    public void createAlarmBox(CreateAlarmBoxRequest request) {
        AlarmBox alarmbox = AlarmBox.builder()
                .status(request.status())
                .content(request.content())
                .user(userFinder.getById(request.userId()))
                .alarmTemplate(alarmTemplateFinder.getById(request.alarmTemplateId()))
                .auctionItem(auctionItemFinder.getById(request.auctionItemId()))
                .build();
        System.out.println(alarmbox);
        alarmBoxRepository.save(alarmbox);
    }

    // 조회
    @Override
    public List<AlarmBoxResponse> findAll() {
        List<AlarmBox> alarmBoxList = alarmBoxRepository.findAll();
        List<AlarmBoxResponse> alarmBoxListResponses = new ArrayList<>();
        for (AlarmBox alarmBox : alarmBoxList) {
            AlarmBoxResponse alarmBoxListResponse = new AlarmBoxResponse(
                    alarmBox.getId(),
                    alarmBox.getStatus(),
                    alarmBox.getContent(),
                    alarmBox.getAlarmTemplate().getId(),
                    alarmBox.getAuctionItem().getId()
            );
            alarmBoxListResponses.add(alarmBoxListResponse);
        }
        return alarmBoxListResponses;
    }

    // 검색
    @Override
    public AlarmBoxResponse findByAlarmBoxId(Long id) {
        Optional<AlarmBox> alarmBox = alarmBoxRepository.findById(id);
        return alarmBox.map(a -> new AlarmBoxResponse(
                a.getId(),
                a.getStatus(),
                a.getContent(),
                a.getAlarmTemplate().getId(),
                a.getAuctionItem().getId()

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

    //페이징
    @Override
    public Page<AlarmBoxResponse> alarmBoxSearch(AlarmBoxSearchRequest request) {
        return alarmBoxRepository.search(request)
                .map(alarmBox-> new AlarmBoxResponse(
                        alarmBox.getUser().getId(),
                        alarmBox.getStatus(),
                        alarmBox.getContent(),
                        alarmBox.getAlarmTemplate().getId(),
                        alarmBox.getAuctionItem().getId()
                ));
    }
}
//    @Override
//    public void changeAlarmBoxStatus(ChangeAlarmBoxStatus req) {
//
//        Id id = alarmBoxRepository.findById(id)
//                .orElseThrow(()
//    }

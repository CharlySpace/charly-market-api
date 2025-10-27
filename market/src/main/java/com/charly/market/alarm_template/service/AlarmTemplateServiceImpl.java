package com.charly.market.alarm_template.service;

import com.charly.market.alarm_template.model.dto.AlarmTemplateChangeContentRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateResponse;
import com.charly.market.alarm_template.model.dto.CreateAlarmTemplateRequest;
import com.charly.market.alarm_template.model.entity.AlarmTemplate;
import com.charly.market.alarm_template.repository.AlarmTemplateRepository;
import com.charly.market.alarmbox.model.dto.CreateAlarmBoxRequest;
import com.charly.market.category.repository.CategoryRepository;
import com.charly.market.category.service.util.CategoryFinder;
import com.charly.market.user.service.util.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmTemplateServiceImpl implements AlarmTemplateService {

    private final AlarmTemplateRepository alarmTemplateRepository;
    private final CategoryFinder categoryFinder;

    // 생성
    @Override
    public void createAlarmTemplate(CreateAlarmTemplateRequest request) {
        //생성자 생성
        AlarmTemplate alarmTemplate = AlarmTemplate.builder()
                .status(request.status())
                .content(request.content())
                .category(categoryFinder.getById(request.categoryId()))
                .build();
        System.out.println(alarmTemplate);
        alarmTemplateRepository.save(alarmTemplate);

    }

    // 조회
    @Override
    public List<AlarmTemplateResponse> findAll() {
        List<AlarmTemplate> alarmTemplateList = alarmTemplateRepository.findAll();
        List<AlarmTemplateResponse> alarmTemplateResponses = new ArrayList<>();
        for (AlarmTemplate alarmTemplate : alarmTemplateList) {
            AlarmTemplateResponse alarmTemplateListResponse = new AlarmTemplateResponse(
                    alarmTemplate.getStatus(),
                    alarmTemplate.getContent()
            );
            alarmTemplateResponses.add(alarmTemplateListResponse);
        }
        return alarmTemplateResponses;
    }

    //검색
    @Override
    public AlarmTemplateResponse findByAlarmTemplateId(Long AlarmTemplateId) {
        Optional<AlarmTemplate> alarmTemplate = alarmTemplateRepository.findById(AlarmTemplateId);
        return alarmTemplate.map(alarmTemplate1 -> new AlarmTemplateResponse(
                alarmTemplate1.getStatus(),
                alarmTemplate1.getContent()
        )).orElse(null);
    }

    //삭제
    @Transactional
    @Override
    public void delete(Long id) {
        AlarmTemplate alarmTemplate = alarmTemplateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AlarmTemplate not found" + id));
        alarmTemplate.deactivatedAlarmTemplateStatus();
    }

    // 수정
    @Transactional
    @Override
    public void changeContent(AlarmTemplateChangeContentRequest req) {
        AlarmTemplate alarmTemplate = alarmTemplateRepository.findById(req.id()).orElseThrow();
        alarmTemplate.changeAlarmTemplateContent(req.content());

    }
}

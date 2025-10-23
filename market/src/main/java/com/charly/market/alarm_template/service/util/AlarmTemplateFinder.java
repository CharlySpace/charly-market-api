package com.charly.market.alarm_template.service.util;

import com.charly.market.alarm_template.model.entity.AlarmTemplate;
import com.charly.market.alarm_template.repository.AlarmTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AlarmTemplateFinder {
    //클래스 선언
    private final AlarmTemplateRepository alarmTemplateRepository;

    //리턴 값은 AlarmTemplate
    public AlarmTemplate getById(Long id){
        return alarmTemplateRepository.findById(id).orElseThrow();
    }

}

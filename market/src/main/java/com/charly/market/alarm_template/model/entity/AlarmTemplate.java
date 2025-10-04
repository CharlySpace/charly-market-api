package com.charly.market.alarm_template.model.entity;

import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.category.model.entity.Category;
import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AlarmTemplate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column
    private String content;


    // fk
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) // DB 컬럼명
    private Category category;


    public void deactivatedAlarmTemplateStatus() {
        this.status = "N";

    }

    public void changeAlarmTemplateContent(String content) {
        this.content = content;
    }

}

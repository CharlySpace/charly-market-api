package com.charly.market.notice.model.entity;

import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private char noticeStatus;

    public void deactivatedNoticeStatus() {
        this.noticeStatus = 'N';
    }

}

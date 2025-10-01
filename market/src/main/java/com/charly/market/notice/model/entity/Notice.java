package com.charly.market.notice.model.entity;

import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
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
    private long id;
    private String title;
    private String content;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // DB 컬럼명
    private User adminUser;

    public void deactivatedNoticeStatus() {
        this.status = "N";
    }

    public void changeNoticeContent(String content) {
        this.content = content;
    }

}

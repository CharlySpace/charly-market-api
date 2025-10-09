package com.charly.market.alarmbox.model.entity;

import com.charly.market.alarm_template.model.entity.AlarmTemplate;
import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
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
public class AlarmBox extends BaseTimeEntity {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private String status;

        @Column
        private String content;


        //fk
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false) // DB 컬럼명
        private User user;

        @ManyToOne(fetch =  FetchType.LAZY)
        @JoinColumn(name = "alarm_id", nullable = false)
        private AlarmTemplate alarmTemplate;

        @ManyToOne(fetch =  FetchType.LAZY)
        @JoinColumn(name = "auction_id")
        private AuctionItem auctionItem;

        public void deactivatedAlarmStatus() {
            this.status = "N";

        }

        public void changeAlarmboxStatus(String status) {
            this.status = status;
        }
}

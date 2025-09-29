package com.charly.market.delivery.model;

import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery extends BaseTimeEntity {
// 배송 db 생성시간 이랑 운송장 등록 시간 컬럼명 서로 바꿨습니다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id; // 배송id

    private String address; // 수령지
    private long deliveryNo; // 운송장 번호
    private String deliveryStatus; // 배송상태
    private LocalDateTime registeredAt; // 운송장번호 등록시간
    private LocalDateTime finishedAt; // 수령 확인 시간


    // fk
    private long sendId; // 보낸사람
    private long receiverId; // 수령한사람
    private long auctionId; // 경매물품 id



    public void insertNo (long deliveryNo){
        this.deliveryNo = deliveryNo;
        this.deliveryStatus = "E";

    }


}

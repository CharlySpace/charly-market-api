package com.charly.market.delivery.model;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
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
    private Long deliveryNo; // 운송장 번호
    private String deliveryStatus; // 배송상태
    private LocalDateTime registeredAt; // 운송장번호 등록시간
    private LocalDateTime finishedAt; // 수령 확인 시간


    // fk
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_user_id" , nullable = false)
    private User sender; // 보낸사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_user_id", nullable = false)
    private User receiver; // 수령한사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = false)
    private AuctionItem auction; // 경매물품 id






    public void registerDeliveryAddress(String address){
        this.address = address;
        this.deliveryStatus = "E";
    }

    public void insertNo (Long deliveryNo){
        this.deliveryNo = deliveryNo;
        this.registeredAt = LocalDateTime.now();
        this.deliveryStatus = "I";

    }

    public void receivedCheck(){
        this.finishedAt = LocalDateTime.now();
        this.deliveryStatus = "S";
    }

    public void returnCheck(){
        this.deliveryStatus = "R";
    }




}

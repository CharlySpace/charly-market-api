package com.charly.market.payment_log.model.entity;

import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="payment_log")

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentLog extends BaseTimeEntity {
    @Id
    @Column(name="payment_log_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long auctionId;//참조키

    private Long bidId;//참조 키

    @Column
    private String type;

    @Column
    private int paymentAmount;

    @Column
    private int conversionAmount;

    //createdAt

    @Column
    private String gradeName;

    @Column
    private int pointAmount;
}

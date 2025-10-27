package com.charly.market.payment_log.model.entity;

import com.charly.market.account.model.entity.Account;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_log")

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentLog extends BaseTimeEntity {

  @Id
  @Column(name = "payment_log_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String type;

  @ManyToOne(fetch = FetchType.LAZY)//LAZY:n+1문제? EAGER
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;//참조키

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;//참조 키


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

package com.charly.market.user.model;

import com.charly.market.global.constant.UserRole;
import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column
  private String id;

  @Column(unique = true)
  private String userEmail;

  @Column
  private String userName;

  @Column(unique = true)
  private String userNickname;

  @Column
  private String userPhone;

  @Column
  private String userPassword;

  @Column
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  @Column
  private String userStatus;

  @Column
  private int userBalance;

  @Column
  private int tradeCount;

  @Column
  private int storedPoint;
}

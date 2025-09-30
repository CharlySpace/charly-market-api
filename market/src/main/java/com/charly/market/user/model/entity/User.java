package com.charly.market.user.model.entity;

import com.charly.market.global.constant.UserRole;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.grade.model.entity.Grade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "users")

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String username; // 설명

  @Column(unique = true)
  private String email;

  @Column
  private String name;

  @Column(unique = true)
  private String nickname;

  @Column
  private String phone;

  @Column
  private String password;

  @Column
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Column
  private String status;

  @Column
  private int balance;

  @Column
  private int tradeCount;

  @Column
  private int storedPoint;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade_id", nullable = false)
  private Grade grade;

  public void deactivatedUserStatus() {
    this.status = "N";
  }

  public void changePassword(String password) {
    this.password = password;
  }

}

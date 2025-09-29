package com.charly.market.account.model.entity;


import com.charly.market.account.model.dto.AccountUpdateRequest;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="account")

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String bankName;

    @Column
    private String accountNumber;

    @Column
    private String bankOwner;

    private Long userId;

    public void changeAccountContent(AccountUpdateRequest request){
        this.bankName= request.bankName();
        this.accountNumber=request.accountNumber();
        this.bankOwner=request.bankOwner();

    }


}

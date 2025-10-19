package com.charly.market.global.mail;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequestDto {
    private String fromEmail;
    private String password;  // 앱 비밀번호 or SMTP 패스워드
    private String toEmail;
    private String code;
}

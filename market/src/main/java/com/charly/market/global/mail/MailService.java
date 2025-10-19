package com.charly.market.global.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender; // Spring에서 자동 주입

    public String sendAuthMail(String toEmail) {
        // 이메일 유효성 체크
        if(toEmail == null || toEmail.isBlank()) {
            throw new IllegalArgumentException("받는 사람 이메일이 비어 있습니다.");
        }

        // 인증 코드 생성
        String authCode = generateAuthCode();

        // 메일 전송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("[CharlyMarket] 이메일 인증 코드");
        message.setText("인증 코드는 [" + authCode + "] 입니다.\n30분 이내에 입력해주세요.");

        mailSender.send(message);

        return authCode;
    }

    private String generateAuthCode() {
        int code = new Random().nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}

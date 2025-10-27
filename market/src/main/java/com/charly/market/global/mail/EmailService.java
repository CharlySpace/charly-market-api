package com.charly.market.global.mail;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final RedisTemplate<String, String> redisTemplate;

    // application.yml에서 TTL 값 가져오기
    @Value("${auth.code.expiration-millis}")
    private long authCodeTTL;

    public EmailService(JavaMailSender mailSender, RedisTemplate<String, String> redisTemplate) {
        this.mailSender = mailSender;
        this.redisTemplate = redisTemplate;
    }

    // 6자리 인증번호 생성
    private String generateAuthCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // 인증번호 발송
    public void sendAuthEmail(String email) {

        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일 주소를 입력해주세요.");
        }


        String code = generateAuthCode();

        // Redis에 TTL 적용하여 저장
        redisTemplate.opsForValue().set("EMAIL_AUTH:" + email, code, authCodeTTL, TimeUnit.MILLISECONDS);

        // 메일 발송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(System.getenv("MAIL_USERNAME")); // 환경변수 기반
        message.setSubject("(charyMarket) 이메일 인증번호 안내");
        message.setText("인증번호: [" + code +"]");

        mailSender.send(message);
    }

    // 인증번호 검증
    public boolean verifyCode(String email, String code) {
        String key = "EMAIL_AUTH:" + email;
        String savedCode = redisTemplate.opsForValue().get(key);

        if(savedCode != null && savedCode.equals(code)) {
            redisTemplate.delete(key); // 인증 성공 시 삭제
            return true;
        }
        return false;
    }
}

package com.charly.market.global.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class MailAuthRepository {

    private final StringRedisTemplate redisTemplate;

    public void saveAuthCode(String email, String code) {
        redisTemplate.opsForValue().set(email, code, 30, TimeUnit.MINUTES);
    }

    public boolean verifyCode(String email, String inputCode) {
        String saved = redisTemplate.opsForValue().get(email);
        return inputCode.equals(saved);
    }
}

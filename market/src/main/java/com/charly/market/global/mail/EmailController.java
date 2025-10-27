package com.charly.market.global.mail;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // 인증번호 발송
    @PostMapping("/send")
    public String sendAuth(@RequestBody EmailRequest req) {
        System.out.println(req.getEmail());
        emailService.sendAuthEmail(req.getEmail());
        return "인증번호 발송 완료!";
    }

    // 인증번호 검증
    @PostMapping("/verify")
    public String verifyAuth(@RequestBody @Valid EmailVerifyRequest req) {
        boolean result = emailService.verifyCode(req.getEmail(), req.getCode());
        return result ? "인증 성공!" : "인증 실패 또는 만료됨";
    }
}


package com.charly.market.global.mail;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final MailAuthRepository mailAuthRepository;

    @PostMapping("/send")
    public String send(@RequestBody MailRequestDto request) {
        String code = mailService.sendAuthMail(request.getToEmail());
        mailAuthRepository.saveAuthCode(request.getToEmail(), code);
        return "인증 코드가 전송되었습니다.";
    }

    @PostMapping("/verify")
    public String verify(@RequestBody MailRequestDto request) {
        boolean valid = mailAuthRepository.verifyCode(request.getToEmail(), request.getCode());
        return valid ? "인증 성공" : "인증 실패";
    }
}
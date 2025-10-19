package com.charly.market.global.mail;



import java.util.Map;

public class SmtpConfig {

    public static final Map<String, MailServerConfig> SMTP_MAP = Map.ofEntries(
            Map.entry("gmail.com", new MailServerConfig("smtp.gmail.com", 587, true, false)),
            Map.entry("naver.com", new MailServerConfig("smtp.naver.com", 587, true, false)),
            Map.entry("daum.net", new MailServerConfig("smtp.daum.net", 465, false, true)),
            Map.entry("kakao.com", new MailServerConfig("smtp.daum.net", 465, false, true)),
            Map.entry("outlook.com", new MailServerConfig("smtp.office365.com", 587, true, false)),
            Map.entry("hotmail.com", new MailServerConfig("smtp.office365.com", 587, true, false))
    );

    public static MailServerConfig get(String emailDomain) {
        // 등록 안 된 도메인은 기본 패턴 smtp.{domain}
        return SMTP_MAP.getOrDefault(emailDomain,
                new MailServerConfig("smtp." + emailDomain, 587, true, false));
    }
}

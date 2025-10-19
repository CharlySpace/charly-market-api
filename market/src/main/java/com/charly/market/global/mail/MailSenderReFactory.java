package com.charly.market.global.mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailSenderReFactory {
    public JavaMailSenderImpl create(String email, String password) {
        String domain = email.substring(email.indexOf("@") + 1);
        MailServerConfig config = SmtpConfig.get(domain);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(config.getHost());
        sender.setPort(config.getPort());
        sender.setUsername(email);
        sender.setPassword(password);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");

        if (config.isStartTls()) props.put("mail.smtp.starttls.enable", "true");
        if (config.isSsl()) props.put("mail.smtp.ssl.enable", "true");

        return sender;
    }
}

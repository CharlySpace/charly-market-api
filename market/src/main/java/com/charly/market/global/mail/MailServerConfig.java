package com.charly.market.global.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailServerConfig {
    private String host;
    private int port;
    private boolean startTls;
    private boolean ssl;
}

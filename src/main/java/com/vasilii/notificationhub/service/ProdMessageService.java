package com.vasilii.notificationhub.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdMessageService implements MessageService {
    @Override
    public void send(String to, String text) {
        System.out.println("[PROD-SMTP] to=" + to + " text=" + text);
    }
}

package com.vasilii.notificationhub.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevMessageService implements MessageService {
    @Override
    public void send(String to, String text) {
        System.out.println("[DEV-MOCK] to=" + to + " text=" + text);
    }
}

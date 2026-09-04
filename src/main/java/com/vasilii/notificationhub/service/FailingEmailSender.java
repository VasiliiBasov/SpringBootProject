package com.vasilii.notificationhub.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev-fail")
public class FailingEmailSender implements EmailSender {

    @Override
    public void send(String recipient, String text) {
        throw new RuntimeException("simulated email failure");
    }
}

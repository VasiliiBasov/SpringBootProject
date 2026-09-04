package com.vasilii.notificationhub.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ConsoleEmailSender implements EmailSender {

    @Override
    public void send(String recipient, String text) {
        System.out.println("Sending email to " + recipient + " text = " + text);
    }
}

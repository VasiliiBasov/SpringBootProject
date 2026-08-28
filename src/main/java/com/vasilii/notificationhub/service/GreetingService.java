package com.vasilii.notificationhub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Value("${app.greeting}")
    private String greeting;

    private final MessageService messageService;

    public GreetingService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void greet() {
        System.out.println(">>> " + greeting);
        messageService.send("user@example.com", greeting);
    }
}

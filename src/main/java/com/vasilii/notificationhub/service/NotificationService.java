package com.vasilii.notificationhub.service;

import com.vasilii.notificationhub.entity.MessageLog;
import com.vasilii.notificationhub.repository.MessageLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    private final MessageLogRepository repository;
    private final EmailSender emailSender;

    public NotificationService(MessageLogRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    @Transactional
    public MessageLog send(String recipient, String text){
        MessageLog saved =  repository.save(new MessageLog(recipient, text));
        emailSender.send(recipient, text);
        return saved;
    }
}

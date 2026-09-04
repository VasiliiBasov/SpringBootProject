package com.vasilii.notificationhub.service;

import com.vasilii.notificationhub.entity.MessageLog;
import com.vasilii.notificationhub.repository.MessageLogRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    private final MessageLogRepository repository;
    private final EmailSender emailSender;
    private final NotificationService self;

    public NotificationService(MessageLogRepository repository, EmailSender emailSender,
                               @Lazy NotificationService self) {
        this.repository = repository;
        this.emailSender = emailSender;
        this.self = self;
    }

    @Transactional
    public MessageLog send(String recipient, String text){
        MessageLog saved =  repository.save(new MessageLog(recipient, text));
        self.auditSend(recipient, text);
        emailSender.send(recipient, text);
        return saved;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MessageLog auditSend(String recipient, String text) {
        String text2 = "Письмо из audit: " + text;
        MessageLog saved =  repository.save(new MessageLog(recipient, text2));
        return saved;
    }
}

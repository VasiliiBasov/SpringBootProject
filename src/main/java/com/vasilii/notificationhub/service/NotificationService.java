package com.vasilii.notificationhub.service;

import com.vasilii.notificationhub.entity.AuditLog;
import com.vasilii.notificationhub.entity.MessageLog;
import com.vasilii.notificationhub.repository.AuditLogRepository;
import com.vasilii.notificationhub.repository.MessageLogRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    private final MessageLogRepository repository;
    private final AuditLogRepository auditLogRepository;
    private final EmailSender emailSender;
    private final NotificationService self;

    public NotificationService(MessageLogRepository repository,
                               AuditLogRepository auditLogRepository,
                               EmailSender emailSender,
                               @Lazy NotificationService self) {
        this.repository = repository;
        this.emailSender = emailSender;
        this.self = self;
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional
    public MessageLog send(String recipient, String text){
        MessageLog saved =  repository.save(new MessageLog(recipient, text));
        self.auditSend(recipient, text);
        emailSender.send(recipient, text);
        return saved;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuditLog auditSend(String eventType, String text) {
        String text2 = "Письмо из audit: " + text;
        AuditLog saved =  auditLogRepository.save(new AuditLog(eventType, text2));
        return saved;
    }
}

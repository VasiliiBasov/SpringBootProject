package com.vasilii.notificationhub.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vasilii.notificationhub.entity.MessageLog;
import com.vasilii.notificationhub.repository.MessageLogRepository;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.List;

@RestController
public class MessageLogController {

    private final MessageLogRepository repository;

    public MessageLogController(MessageLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/messages/search")
    public List<MessageLog> search(
            @RequestParam(required = false) String recipient,
            @RequestParam(name = "q", required = false) String text,
            @RequestParam(required = false) Instant from) {
        Specification<MessageLog> spec = Specification.unrestricted();
        if (recipient != null && !recipient.isBlank()) {
            spec = spec.and(MessageLogRepository.hasRecipient(recipient));
        }
        if (text != null && !text.isBlank()) {
            spec = spec.and(MessageLogRepository.textContains(text));
        }
        if (from != null) {
            spec = spec.and(MessageLogRepository.createdAfter(from));
        }

        return repository.findAll(spec);
    }
}

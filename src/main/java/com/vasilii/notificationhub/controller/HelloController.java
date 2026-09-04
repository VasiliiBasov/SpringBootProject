package com.vasilii.notificationhub.controller;


import com.vasilii.notificationhub.dto.MessageRequest;
import com.vasilii.notificationhub.entity.MessageLog;
import com.vasilii.notificationhub.repository.MessageLogRepository;
import com.vasilii.notificationhub.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class HelloController {

    private final MessageLogRepository repository;
    private final NotificationService notificationService;

    public HelloController(MessageLogRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @PostMapping("/messages")
    public ResponseEntity<MessageLog> createMessage(
            @Valid @RequestBody MessageRequest req) {

        MessageLog saved = notificationService.send(req.getRecipient(), req.getText());


        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/messages")
    public List<MessageLog> listMessages() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
}

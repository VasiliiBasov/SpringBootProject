package com.vasilii.notificationhub.controller;


import com.vasilii.notificationhub.dto.MessageRequest;
import com.vasilii.notificationhub.entity.MessageLog;
import com.vasilii.notificationhub.repository.MessageLogRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class HelloController {

    private final MessageLogRepository repository;

    public HelloController(MessageLogRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/messages")
    public ResponseEntity<MessageLog> createMessage(
            @Valid @RequestBody MessageRequest req) {

        MessageLog saved = repository.save(new MessageLog(req.getRecipient(), req.getText()));


        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/messages")
    public List<MessageLog> listMessages() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
}

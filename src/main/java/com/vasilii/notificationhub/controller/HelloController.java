package com.vasilii.notificationhub.controller;


import com.vasilii.notificationhub.dto.MessageRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @PostMapping("/messages")
    public Map<String, Object> createMessage(
            @Valid @RequestBody MessageRequest req) {
        return Map.of(
                "received", true,
                "to", req.getTo(),
                "text", req.getText(),
                "length", req.getText().length()
        );
    }
}

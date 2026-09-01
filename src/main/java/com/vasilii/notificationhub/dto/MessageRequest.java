package com.vasilii.notificationhub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageRequest {
    @NotBlank(message = "recipient обязательно")
    @Email(message = "recipient должен быть валидным email")
    private String recipient;

    @NotBlank(message = "text обязательно")
    @Size(max = 1000, message = "text слишком длинный (максимум 1000 символов)")
    private String text;

    public MessageRequest() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

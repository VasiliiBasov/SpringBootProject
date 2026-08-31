package com.vasilii.notificationhub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageRequest {
    @NotBlank(message = "to обязательно")
    @Email(message = "to должен быть валидным уьфшд")
    private String to;

    @NotBlank(message = "text обязательно")
    @Size(max = 1000, message = "text слишком длинный (максимум 1000 символов)")
    private String text;

    public MessageRequest() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

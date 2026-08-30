package com.vasilii.notificationhub.dto;

public class MessageRequest {
    private String to;
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

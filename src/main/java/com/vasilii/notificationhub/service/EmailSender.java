package com.vasilii.notificationhub.service;

public interface EmailSender {
    void send(String recipient, String text);
}

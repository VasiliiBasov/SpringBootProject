package com.vasilii.notificationhub.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "messages")
public class MessageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipient", nullable = false, length = 100)
    private String recipient;

    @Column(name = "text", nullable = false, length = 10000)
    private String text;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public MessageLog() {
    }

    public MessageLog(String recipient, String text) {
        this.recipient = recipient;
        this.text = text;
        this.createdAt = Instant.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}


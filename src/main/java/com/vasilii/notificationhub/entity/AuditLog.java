package com.vasilii.notificationhub.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type", length = 100, nullable = false)
    private String eventType;

    @Column(name = "message", length = 10017)
    private String message;

    @Column(name = "created_at")
    private Instant createdAt;

    public AuditLog(String eventType, String message) {
        this.eventType = eventType;
        this.message = message;
        this.createdAt = Instant.now();
    }

    public AuditLog() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

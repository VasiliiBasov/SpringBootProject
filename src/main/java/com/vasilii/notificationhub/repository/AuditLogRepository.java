package com.vasilii.notificationhub.repository;

import com.vasilii.notificationhub.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {


    @Query(value = "SELECT * FROM audit_log WHERE event_type = :eventType " +
            "ORDER BY created_at DESC", nativeQuery = true)
    List<AuditLog> findByEventTypeNative(@Param("eventType") String eventType);

}
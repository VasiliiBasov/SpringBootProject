package com.vasilii.notificationhub.repository;

import com.vasilii.notificationhub.entity.MessageLog;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Locale;

@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, Long>, JpaSpecificationExecutor<MessageLog> {

    static Specification<MessageLog> hasRecipient(String recipient) {
        return (root, query, criteriaBuilder) -> {
            return (recipient == null ? null : criteriaBuilder.equal(root.get("recipient"), recipient));
        };
    }
    static Specification<MessageLog> textContains(String text) {
        return (root, query, criteriaBuilder) -> {
            return (text == null ? null : criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("text")),
                    "%" + text.toLowerCase(Locale.ROOT) + "%"));
        };
    }
    static Specification<MessageLog> createdAfter(Instant from) {
        return (root, query, criteriaBuilder) -> {
            return (from == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), from));
        };
    }

    List<MessageLog> findAllByOrderByCreatedAtDesc();

    @Query("SELECT m FROM MessageLog m WHERE m.recipient = :recipient ORDER BY m.createdAt DESC")
    List<MessageLog> findByRecipientOrderByCreatedAtDesc(@Param("recipient") String recipient);

    @Query("SELECT m FROM MessageLog m WHERE m.createdAt BETWEEN :timeFrom AND :timeTo ORDER BY m.createdAt DESC")
    List<MessageLog> findByCreatedAtBetween(
            @Param("timeFrom") Instant timeFrom,
            @Param("timeTo") Instant timeTo);
}

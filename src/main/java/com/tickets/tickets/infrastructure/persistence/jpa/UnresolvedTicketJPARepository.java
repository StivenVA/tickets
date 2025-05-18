package com.tickets.tickets.infrastructure.persistence.jpa;

import com.tickets.tickets.infrastructure.persistence.entities.UnResolvedTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UnresolvedTicketJPARepository extends JpaRepository<UnResolvedTicketEntity, Long> {

    @Query("select u from UnResolvedTicketEntity u where u.storedAt < :dateThreshold")
    List<UnResolvedTicketEntity> findPassed30Days(@Param("dateThreshold") LocalDateTime dateThreshold);

    @Procedure(name = "StoreUnresolvedTickets")
    void storeUnresolvedTickets();
}

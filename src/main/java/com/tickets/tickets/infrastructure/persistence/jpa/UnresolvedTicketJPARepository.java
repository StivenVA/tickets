package com.tickets.tickets.infrastructure.persistence.jpa;

import com.tickets.tickets.domain.model.UnresolvedTicket;
import com.tickets.tickets.infrastructure.persistence.entities.UnResolvedTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnresolvedTicketJPARepository extends JpaRepository<UnResolvedTicketEntity, Long> {

    @Query("select u from UnResolvedTicketEntity u where u.ticketId.createdAt > current_date - interval ('30 days')")
    List<UnResolvedTicketEntity> findPassed30Days();
}

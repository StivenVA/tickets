package com.tickets.infrastructure.persistence.jpa;


import com.tickets.infrastructure.persistence.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJPARepository extends JpaRepository<TicketEntity, Long> {

}

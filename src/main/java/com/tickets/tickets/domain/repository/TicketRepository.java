package com.tickets.tickets.domain.repository;

import com.tickets.tickets.domain.model.Ticket;

import java.util.List;

public interface TicketRepository {

    List<Ticket> findAll();
    Ticket findById(Long id);
    Ticket save(Ticket ticket);
}

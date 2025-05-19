package com.tickets.domain.repository;

import com.tickets.domain.model.Ticket;

import java.util.List;

public interface TicketRepository {

    List<Ticket> findAll();
    Ticket findById(Long id);
    Ticket save(Ticket ticket);
}

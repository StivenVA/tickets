package com.tickets.tickets.application.usecase;

import com.tickets.tickets.domain.model.Ticket;

import java.util.List;

public interface TicketUseCase {

    Ticket save(String title, String description);

    Ticket findById(Long id);

    List<Ticket> findAll();

    Ticket updateStatus(Ticket ticket);

    Ticket resolve(Ticket ticket);
}

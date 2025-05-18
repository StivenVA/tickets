package com.tickets.tickets.application.usecase;

import com.tickets.tickets.domain.model.Ticket;

import java.util.List;

public interface TicketUseCase {

    Ticket create(String title, String description);

    Ticket getById(Long id);

    List<Ticket> getAll();

    Ticket updateStatus(Ticket ticket);

    Ticket resolve(Ticket ticket);
}

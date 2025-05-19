package com.tickets.application.usecase;

import com.tickets.domain.model.Ticket;

import java.util.List;

public interface TicketUseCase {

    Ticket create(String title, String description);

    Ticket getById(Long id);

    List<Ticket> getAll();

    Ticket updateStatus(Long id);

    Ticket resolve(Long id,String comment);
}

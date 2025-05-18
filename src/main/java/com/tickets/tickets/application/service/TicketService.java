package com.tickets.tickets.application.service;

import com.tickets.tickets.application.usecase.TicketUseCase;
import com.tickets.tickets.domain.model.Ticket;
import com.tickets.tickets.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketService implements TicketUseCase {

    private final TicketRepository ticketRepository;
    private final ZoneId colombiaZoneId = ZoneId.of("America/Bogota");

    @Override
    public Ticket create(String title, String description) {

        LocalDateTime now = LocalDateTime.now(colombiaZoneId).withNano(0);

        Ticket ticket = Ticket.builder()
                .createdAt(now)
                .status("Open")
                .title(title)
                .description(description)
                .expiredAt(LocalDateTime.of(now.toLocalDate(), LocalTime.MAX).withNano(0))
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket updateStatus(Long id) {

        Ticket ticketToUpdate = ticketRepository.findById(id);

        switch (ticketToUpdate.getStatus()){
            case "OPEN" -> ticketToUpdate.setStatus("In Progress");
            case "IN_PROGRESS" -> ticketToUpdate.setStatus("Resolved");
            case "RESOLVED" -> ticketToUpdate.setStatus("Closed");
            case "CLOSED" -> throw new IllegalArgumentException("Cannot change status of a close ticket");
            default -> throw new IllegalArgumentException("Invalid status: ");
        }

        ticketToUpdate.setUpdatedAt(LocalDateTime.now(colombiaZoneId).withNano(0));

        return ticketRepository.save(ticketToUpdate);

    }

    @Override
    public Ticket resolve(Long id, String comment) {

        Ticket ticketToSolve = ticketRepository.findById(id);

        if(ticketToSolve.getStatus().equals("CLOSED")){
            throw new IllegalArgumentException("Cannot resolve a closed ticket");
        }

        ticketToSolve.setStatus("Resolved");

        if(comment != null && !comment.isEmpty()){
            ticketToSolve.setComment(comment);
        }

        ticketToSolve.setUpdatedAt(LocalDateTime.now(colombiaZoneId).withNano(0));

        return ticketRepository.save(ticketToSolve);
    }

}

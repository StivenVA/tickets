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

        LocalDateTime now = LocalDateTime.now(colombiaZoneId);

        Ticket ticket = Ticket.builder()
                .createdAt(now)
                .status("Open")
                .title(title)
                .description(description)
                .expiredAt(LocalDateTime.of(now.toLocalDate(), LocalTime.MAX))
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
    public Ticket updateStatus(Ticket ticket){

        Ticket ticketToUpdate = ticketRepository.findById(ticket.getId());
        ticketToUpdate.setStatus(ticket.getStatus());
        ticketToUpdate.setUpdatedAt(LocalDateTime.now(colombiaZoneId));

        if(ticket.getStatus().equals("Resolved") && ticket.getComment()!= null){
            ticketToUpdate.setComment(ticket.getComment());
        }

        return ticketRepository.save(ticketToUpdate);

    }

    @Override
    public Ticket resolve(Ticket ticket) {
        ticket.setStatus("Resolved");
        return updateStatus(ticket);
    }

}

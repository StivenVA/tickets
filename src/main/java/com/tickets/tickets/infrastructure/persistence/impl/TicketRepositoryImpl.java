package com.tickets.tickets.infrastructure.persistence.impl;

import com.tickets.tickets.domain.model.Ticket;
import com.tickets.tickets.domain.repository.TicketRepository;
import com.tickets.tickets.infrastructure.persistence.entities.TicketEntity;
import com.tickets.tickets.infrastructure.persistence.entities.TicketStatus;
import com.tickets.tickets.infrastructure.persistence.jpa.TicketJPARepository;
import com.tickets.tickets.infrastructure.persistence.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final TicketJPARepository ticketJpaRepository;

    @Override
    public List<Ticket> findAll() {
        return ticketJpaRepository.findAll().stream().map(TicketMapper::toModel).toList();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketJpaRepository.findById(id).map(TicketMapper::toModel).orElseThrow(()->
                new RuntimeException("No ticket with id " + id));
    }

    @Override
    public Ticket save(Ticket ticket) {

        TicketEntity ticketEntity = mapToEntity(ticket);

        return Optional.of(TicketMapper.toModel(ticketEntity))
                .orElseThrow(()-> new RuntimeException("An error occurred while saving the ticket"));
    }

    private TicketEntity mapToEntity(Ticket ticket) {
        return TicketEntity.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(TicketStatus.fromString(ticket.getStatus()))
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .comment(ticket.getComment())
                .build();
    }

}

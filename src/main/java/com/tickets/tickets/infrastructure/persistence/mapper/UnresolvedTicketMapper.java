package com.tickets.tickets.infrastructure.persistence.mapper;

import com.tickets.tickets.application.dto.ResponseUnresolvedTicket;
import com.tickets.tickets.domain.model.UnresolvedTicket;
import com.tickets.tickets.infrastructure.persistence.entities.UnResolvedTicketEntity;

public class UnresolvedTicketMapper {

    public static UnresolvedTicket toModel(UnResolvedTicketEntity unresolvedTicketEntity) {
        return UnresolvedTicket.builder()
                .id(unresolvedTicketEntity.getId())
                .ticket(TicketMapper.toModel(unresolvedTicketEntity.getTicketId()))
                .storedAt(unresolvedTicketEntity.getStoredAt())
                .build();
    }

    public static UnResolvedTicketEntity toEntity(UnresolvedTicket unresolvedTicket) {
        return UnResolvedTicketEntity.builder()
                .id(unresolvedTicket.getId())
                .ticketId(TicketMapper.toEntity(unresolvedTicket.getTicket()))
                .storedAt(unresolvedTicket.getStoredAt())
                .build();
    }

    public static ResponseUnresolvedTicket toResponseDTO(UnresolvedTicket unresolvedTicket) {
        return ResponseUnresolvedTicket.builder()
                .ticket(TicketMapper.toResponseDTO(unresolvedTicket.getTicket()))
                .storedAt(unresolvedTicket.getStoredAt())
                .build();
    }
}

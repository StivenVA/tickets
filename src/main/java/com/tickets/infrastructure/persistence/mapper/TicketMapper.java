package com.tickets.infrastructure.persistence.mapper;

import com.tickets.application.dto.ResponseTicket;
import com.tickets.domain.model.Ticket;
import com.tickets.infrastructure.persistence.entities.TicketEntity;
import com.tickets.infrastructure.persistence.entities.TicketStatus;

public final class TicketMapper {

    private TicketMapper() {}

    public static Ticket toModel(TicketEntity entity) {
        return Ticket.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .description(entity.getDescription())
            .status(entity.getStatus().name())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
                .expiredAt(entity.getExpiredAt())
            .comment(entity.getComment())
            .build();
    }

    public static TicketEntity toEntity(Ticket model) {
        return TicketEntity.builder()
            .id(model.getId()==null?0:model.getId())
            .title(model.getTitle())
            .description(model.getDescription())
            .status(TicketStatus.fromString(model.getStatus()))
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
                .expiredAt(model.getExpiredAt())
            .comment(model.getComment())
            .build();
    }

    public static ResponseTicket toResponseDTO(Ticket ticket) {
        return ResponseTicket.builder()
            .id(ticket.getId())
            .title(ticket.getTitle())
            .description(ticket.getDescription())
            .status(ticket.getStatus())
            .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
            .comment(ticket.getComment())
                .expiredAt(ticket.getExpiredAt())
            .build();
    }
}
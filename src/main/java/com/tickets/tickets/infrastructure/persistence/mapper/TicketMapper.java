package com.tickets.tickets.infrastructure.persistence.mapper;

import com.tickets.tickets.domain.model.Ticket;
import com.tickets.tickets.infrastructure.persistence.entities.TicketEntity;
import com.tickets.tickets.infrastructure.persistence.entities.TicketStatus;

public class TicketMapper {
    public static Ticket toModel(TicketEntity entity) {
        return Ticket.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .description(entity.getDescription())
            .status(entity.getStatus().name())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .comment(entity.getComment())
            .build();
    }

    public static TicketEntity toEntity(Ticket model) {
        return TicketEntity.builder()
            .id(model.getId())
            .title(model.getTitle())
            .description(model.getDescription())
            .status(TicketStatus.valueOf(model.getStatus()))
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
            .comment(model.getComment())
            .build();
    }
}
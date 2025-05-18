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
}
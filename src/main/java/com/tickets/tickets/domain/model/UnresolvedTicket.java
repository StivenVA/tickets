package com.tickets.tickets.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UnresolvedTicket {

    private Long id;
    private Ticket ticket;
    private LocalDateTime createdAt;
}

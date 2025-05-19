package com.tickets.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UnresolvedTicket {

    private Long id;
    private Ticket ticket;
    private LocalDateTime storedAt;
}

package com.tickets.tickets.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUnresolvedTicket {
    private ResponseTicket ticket;
    private LocalDateTime storedAt;
}

package com.tickets.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ResponseUnresolvedTicket {
    private ResponseTicket ticket;
    private LocalDateTime storedAt;
}

package com.tickets.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResolveTicketRequest {

    private Long id;
    private String comment;
}

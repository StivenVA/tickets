package com.tickets.application.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateTicketRequest {

    private String title;
    private String description;
}

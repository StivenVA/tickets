package com.tickets.tickets.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTicket {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String comment;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}

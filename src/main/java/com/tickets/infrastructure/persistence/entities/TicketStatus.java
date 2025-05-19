package com.tickets.infrastructure.persistence.entities;

import lombok.Getter;

@Getter
public enum TicketStatus {

    OPEN("Open", "Abierto"),
    IN_PROGRESS("In Progress", "En progreso"),
    RESOLVED("Resolved", "Resuelto"),
    CLOSED("Closed", "Cerrado");

    private final String statusEn;
    private final String statusEs;

    TicketStatus(String statusEn, String statusEs) {
        this.statusEn = statusEn;
        this.statusEs = statusEs;
    }

    public static TicketStatus fromString(String status) {
        for (TicketStatus ticketStatus : TicketStatus.values()) {
            if (ticketStatus.statusEn.equalsIgnoreCase(status) || ticketStatus.statusEs.equalsIgnoreCase(status)) {
                return ticketStatus;
            }
        }
        throw new IllegalArgumentException("No enum constant " + TicketStatus.class.getCanonicalName() + "." + status);
    }
}

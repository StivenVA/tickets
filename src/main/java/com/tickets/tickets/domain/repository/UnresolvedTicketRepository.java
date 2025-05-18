package com.tickets.tickets.domain.repository;

import com.tickets.tickets.domain.model.UnresolvedTicket;

import java.util.List;

public interface UnresolvedTicketRepository {

    List<UnresolvedTicket> findPassed30Days();

    UnresolvedTicket save(UnresolvedTicket unresolvedTicket);

}

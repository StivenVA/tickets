package com.tickets.tickets.infrastructure.persistence.impl;

import com.tickets.tickets.domain.model.Ticket;
import com.tickets.tickets.domain.model.UnresolvedTicket;
import com.tickets.tickets.domain.repository.UnresolvedTicketRepository;
import com.tickets.tickets.infrastructure.persistence.entities.TicketEntity;
import com.tickets.tickets.infrastructure.persistence.entities.UnResolvedTicketEntity;
import com.tickets.tickets.infrastructure.persistence.jpa.UnresolvedTicketJPARepository;
import com.tickets.tickets.infrastructure.persistence.mapper.UnresolvedTicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UnresolvedTicketRepositoryImpl implements UnresolvedTicketRepository {

    private final UnresolvedTicketJPARepository unresolvedTicketJPARepository;

    @Override
    public List<UnresolvedTicket> findPassed30Days() {
        LocalDateTime dateThreshold = LocalDateTime.now().minusDays(30);
        return unresolvedTicketJPARepository.findPassed30Days(dateThreshold)
                .stream()
                .map(UnresolvedTicketMapper::toModel).toList();
    }

    @Override
    public UnresolvedTicket save(UnresolvedTicket unresolvedTicket) {

        UnResolvedTicketEntity unResolvedTicketEntity = UnresolvedTicketMapper.toEntity(unresolvedTicket);

        return Optional.of(unresolvedTicketJPARepository.save(unResolvedTicketEntity))
                .map(UnresolvedTicketMapper::toModel)
                .orElseThrow(() -> new RuntimeException("An error occurred while saving the unresolved ticket"));
    }

    public void runStoreUnresolvedTicketsProcedure() {
        unresolvedTicketJPARepository.storeUnresolvedTickets();
    }

}

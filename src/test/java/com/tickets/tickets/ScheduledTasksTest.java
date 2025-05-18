package com.tickets.tickets;

import com.tickets.tickets.application.service.UnresolvedTicketService;
import com.tickets.tickets.application.usecase.UnresolvedTicketUseCase;
import com.tickets.tickets.infrastructure.scheduler.ScheduledTasks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ScheduledTasksTest {

    @Test
    void runDailyProcedure_shouldCallServiceMethod() {
        UnresolvedTicketUseCase unresolvedTicketService = mock(UnresolvedTicketService.class);
        ScheduledTasks tasks = new ScheduledTasks(unresolvedTicketService);

        tasks.runDailyProcedure();
        tasks.runDailyProcedure();

        verify(unresolvedTicketService, times(2)).runStoreUnresolvedTicketsProcedure();

    }
}

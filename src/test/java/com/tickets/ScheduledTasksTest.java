package com.tickets;

import com.tickets.application.service.UnresolvedTicketService;
import com.tickets.application.usecase.UnresolvedTicketUseCase;
import com.tickets.infrastructure.scheduler.ScheduledTasks;
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

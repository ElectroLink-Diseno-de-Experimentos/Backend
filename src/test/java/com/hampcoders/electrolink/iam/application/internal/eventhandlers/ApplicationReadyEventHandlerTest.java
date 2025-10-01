package com.hampcoders.electrolink.iam.application.internal.eventhandlers;

import com.hampcoders.electrolink.iam.domain.model.commands.SeedRolesCommand;
import com.hampcoders.electrolink.iam.domain.services.RoleCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationReadyEventHandlerTest {
    @Mock
    private RoleCommandService roleCommandService;

    @InjectMocks
    private ApplicationReadyEventHandler handler;

    @Test
    @DisplayName("on(ApplicationReadyEvent) should delegate seeding to RoleCommandService (AAA)")
    void on_ShouldDelegateSeeding() {
        // Arrange
        ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class);
        when(context.getId()).thenReturn("iam-app");
        ApplicationReadyEvent event = mock(ApplicationReadyEvent.class);
        when(event.getApplicationContext()).thenReturn(context);

        // Act
        handler.on(event);

        // Assert
        verify(event).getApplicationContext();
        verify(context).getId();
        verify(roleCommandService).handle(any(SeedRolesCommand.class));
        verifyNoMoreInteractions(roleCommandService, event, context);
    }
}

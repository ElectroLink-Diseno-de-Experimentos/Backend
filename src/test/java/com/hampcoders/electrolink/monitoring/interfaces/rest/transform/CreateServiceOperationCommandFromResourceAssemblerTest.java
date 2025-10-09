package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateServiceOperationResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CreateServiceOperationCommandFromResourceAssemblerTest {
    @Test
    @DisplayName("toCommandFromResource should map resource and assign default values correctly (AAA)")
    void toCommandFromResource_ShouldMapResourceAndAssignDefaults() {
        // Arrange
        Long requestId = 50L;
        Long technicianId = 99L;

        OffsetDateTime fixedTime = OffsetDateTime.parse("2025-10-05T10:00:00Z");

        var resource = new CreateServiceOperationResource(requestId, technicianId);

        try (MockedStatic<OffsetDateTime> mockedStatic = Mockito.mockStatic(OffsetDateTime.class)) {
            mockedStatic.when(OffsetDateTime::now).thenReturn(fixedTime);

            // Act
            var command = CreateServiceOperationCommandFromResourceAssembler.toCommandFromResource(resource);

            // Assert
            assertNotNull(command, "The command should not be null.");
            assertEquals(new RequestId(requestId), command.requestId(), "RequestId should be mapped correctly.");
            assertEquals(new TechnicianId(technicianId), command.technicianId(), "TechnicianId should be mapped correctly.");
            assertEquals(fixedTime, command.startedAt(), "startedAt should be set to the mocked current time.");
            assertNull(command.completedAt(), "completedAt should be null by default.");
            assertEquals(ServiceStatus.IN_PROGRESS, command.currentStatus(), "currentStatus should default to IN_PROGRESS.");
        }
    }
}

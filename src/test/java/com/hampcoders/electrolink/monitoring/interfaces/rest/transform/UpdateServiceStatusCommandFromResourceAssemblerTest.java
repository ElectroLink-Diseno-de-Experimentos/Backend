package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateServiceStatusResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateServiceStatusCommandFromResourceAssemblerTest {
    @Test
    @DisplayName("toCommandFromResource should correctly map UpdateServiceStatusResource to UpdateServiceStatusCommand (AAA)")
    void toCommandFromResource_ShouldMapResourceToCommand() {
        // Arrange
        Long serviceOperationId = 40L;
        String status = "COMPLETED";

        var resource = new UpdateServiceStatusResource(serviceOperationId, status);

        // Act
        UpdateServiceStatusCommand command = UpdateServiceStatusCommandFromResourceAssembler.toCommandFromResource(resource);

        // Assert
        assertNotNull(command, "El comando retornado no debe ser nulo.");

        assertEquals(serviceOperationId, command.serviceOperationId(), "El Service Operation ID debe coincidir.");
        assertEquals(status, command.newStatus(), "El nuevo estado debe coincidir.");
    }
}

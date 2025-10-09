package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.ReportType;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateReportCommandFromResourceAssemblerTest {
    @Test
    @DisplayName("toCommandFromResource should correctly map CreateReportResource to AddReportCommand (AAA)")
    void toCommandFromResource_ShouldMapResourceToCommand() {
        // Arrange
        Long serviceOperationId = 100L;
        String reportType = "INCIDENT";
        String description = "Initial diagnostic run on the main transformer.";

        var resource = new CreateReportResource(serviceOperationId, reportType, description);

        // Act
        var command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);

        // Assert
        assertNotNull(command, "El comando retornado no debe ser nulo.");
        assertEquals(serviceOperationId, command.serviceOperationId(),
                "El RequestId debe ser mapeado y envuelto en un Value Object.");
        assertEquals(ReportType.INCIDENT, command.reportType(),
                "El tipo de reporte debe ser mapeado y convertido al enum ReportType.");
        assertEquals(description, command.description(),
                "La descripción debe coincidir.");
    }

    @Test
    @DisplayName("toCommandFromResource should throw IllegalArgumentException for invalid ReportType string (AAA)")
    void toCommandFromResource_ShouldThrowException_WhenReportTypeIsInvalid() {
        // Arrange
        var resource = new CreateReportResource(
                1L,
                "INVALID_TYPE", // Este valor no existe en el enum ReportType
                "Test description"
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
        }, "Debe lanzar IllegalArgumentException si la cadena de ReportType es inválida para el enum.");
    }
}
package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportType;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReportResourceFromEntityAssemblerTest {
    @Test
    @DisplayName("toResourceFromEntity should correctly map Report entity to ReportResource (AAA)")
    void toResourceFromEntity_ShouldMapEntityToResource() {
        // Arrange
        Long reportId = 1L;
        Long requestId = 20L;
        String description = "Faulty wiring detected.";
        ReportType reportType = ReportType.MAINTENANCE;

        var requestIdVo = mock(RequestId.class);
        when(requestIdVo.getId()).thenReturn(requestId);

        Report entity = mock(Report.class);
        when(entity.getId()).thenReturn(reportId);
        when(entity.getRequestId()).thenReturn(requestIdVo);
        when(entity.getDescription()).thenReturn(description);
        when(entity.getReportType()).thenReturn(reportType);

        // Act
        ReportResource resource = ReportResourceFromEntityAssembler.toResourceFromEntity(entity);

        // Assert
        assertNotNull(resource, "El recurso retornado no debe ser nulo.");

        assertEquals(reportId, resource.id(), "El ID del Reporte debe coincidir.");
        assertEquals(requestId, resource.requestId(),
                "El RequestId debe extraerse correctamente del Value Object.");
        assertEquals(description, resource.description(),
                "La descripción debe coincidir.");
        assertEquals(reportType.name(), resource.reportType(),
                "El ReportType debe convertirse a String (nombre del enum).");

        verify(entity).getId();
        verify(entity).getRequestId();
        verify(entity).getDescription();
        verify(entity).getReportType();
        verify(requestIdVo).getId();
        verifyNoMoreInteractions(entity, requestIdVo);
    }
}

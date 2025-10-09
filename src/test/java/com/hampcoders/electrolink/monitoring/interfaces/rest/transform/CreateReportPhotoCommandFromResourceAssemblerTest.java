package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportPhotoResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateReportPhotoCommandFromResourceAssemblerTest {
    @Test
    @DisplayName("toCommandFromResource should correctly map CreateReportPhotoResource to AddPhotoCommand (AAA)")
    void toCommandFromResource_ShouldMapResourceToCommand() {
        // Arrange
        Long reportId = 42L;
        String url = "http://storage.electrolink.com/reports/42/photo_xyz.jpg";

        var resource = new CreateReportPhotoResource(reportId, url);

        // Act
        var command = CreateReportPhotoCommandFromResourceAssembler.toCommandFromResource(resource);

        // Assert
        assertNotNull(command, "El comando retornado no debe ser nulo.");
        assertEquals(new ReportId(reportId), command.reportId(),
                "El ReportId debe ser mapeado y envuelto en un Value Object.");
        assertEquals(url, command.url(), "La URL debe coincidir.");
        assertNotNull(command.reportPhotoId(), "El ReportPhotoId debe generarse automáticamente y no ser nulo.");
        assertInstanceOf(ReportPhotoId.class, command.reportPhotoId(),
                "El ID generado debe ser una instancia de ReportPhotoId.");
    }
}

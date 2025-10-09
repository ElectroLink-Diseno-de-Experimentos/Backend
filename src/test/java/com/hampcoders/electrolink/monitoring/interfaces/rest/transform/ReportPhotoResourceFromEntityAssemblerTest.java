package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportPhotoResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReportPhotoResourceFromEntityAssemblerTest {
    @Test
    @DisplayName("toResourceFromEntity should correctly map ReportPhoto entity to ReportPhotoResource (AAA)")
    void toResourceFromEntity_ShouldMapEntityToResource() {
        // Arrange
        Long photoId = 77L;
        Long reportId = 88L;
        String url = "https://storage.electrolink.com/photos/xyz.jpg";

        var entity = mock(ReportPhoto.class);
        when(entity.getId()).thenReturn(photoId);
        when(entity.getReportId()).thenReturn(reportId);
        when(entity.getUrl()).thenReturn(url);

        // Act
        ReportPhotoResource resource = ReportPhotoResourceFromEntityAssembler.toResourceFromEntity(entity);

        // Assert
        assertNotNull(resource, "El recurso retornado no debe ser nulo.");

        assertEquals(photoId, resource.id(), "El ID de la foto debe extraerse correctamente.");
        assertEquals(reportId, resource.reportId(), "El ReportId debe extraerse correctamente.");
        assertEquals(url, resource.url(), "La URL debe coincidir.");

        verify(entity).getId();
        verify(entity).getReportId();
        verify(entity).getUrl();
        verifyNoMoreInteractions(entity);
    }
}
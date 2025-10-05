package com.hampcoders.electrolink.monitoring.application.internal.commandservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;
import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportPhotoRepository;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportPhotoCommandServiceImplTest {
    @Mock
    private ReportPhotoRepository reportPhotoRepository;
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportPhotoCommandServiceImpl reportPhotoCommandService;

    @Test
    @DisplayName("handle(AddPhotoCommand) should save photo when Report exists (AAA)")
    void handle_AddPhotoCommand_ShouldSavePhotoAndReturnId_WhenReportExists() {
        // Arrange
        ReportId reportId = new ReportId(10L);
        var url = "http://example.com/photo.jpg";

        when(reportRepository.findById(eq(reportId))).thenReturn(Optional.of(mock(Report.class)));
        when(reportPhotoRepository.save(any(ReportPhoto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var command = new AddPhotoCommand(new ReportPhotoId(), reportId, url);

        // Act
        var actualId = reportPhotoCommandService.handle(command);

        // Assert
        assertNull(actualId, "Expected id to be null because repository save is mocked and no id is set");

        verify(reportRepository, times(1)).findById(reportId);
        verify(reportPhotoRepository, times(1)).save(any(ReportPhoto.class));
        verifyNoMoreInteractions(reportRepository, reportPhotoRepository);
    }

    @Test
    @DisplayName("handle(AddPhotoCommand) should throw IllegalArgumentException if Report not found (AAA)")
    void handle_AddPhotoCommand_ShouldThrowException_WhenReportNotFound() {
        // Arrange
        ReportId reportId = new ReportId(10L);
        var url = "http://example.com/photo.jpg";

        when(reportRepository.findById(eq(reportId))).thenReturn(Optional.empty());

        var command = new AddPhotoCommand(new ReportPhotoId(), reportId, url);

        // Act + Assert
        var ex = assertThrows(IllegalArgumentException.class, () -> {
            reportPhotoCommandService.handle(command);
        }, "Debe lanzar IllegalArgumentException si el Reporte no existe.");

        assertTrue(ex.getMessage().contains("Report not found with ID: "));

        verify(reportRepository, times(1)).findById(reportId);
        verify(reportPhotoRepository, never()).save(any(ReportPhoto.class));
        verifyNoMoreInteractions(reportRepository);
        verifyNoInteractions(reportPhotoRepository);
    }
}

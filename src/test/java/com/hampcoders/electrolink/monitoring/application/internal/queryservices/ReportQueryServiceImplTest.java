package com.hampcoders.electrolink.monitoring.application.internal.queryservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.queries.*;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class ReportQueryServiceImplTest {
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportQueryServiceImpl reportQueryService;

    @Test
    @DisplayName("handle(GetAllReportsQuery) should return the list from repository (AAA)")
    void handle_GetAllReportsQuery_ShouldReturnAllReports() {
        // Arrange
        Report a = mock(Report.class);
        Report b = mock(Report.class);

        when(reportRepository.findAll()).thenReturn(List.of(a, b));
        var query = new GetAllReportsQuery();

        // Act
        var actual = reportQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size(), "Must return a list with 2 Reports.");
        assertSame(a, actual.get(0));
        assertSame(b, actual.get(1));
        verify(reportRepository).findAll();
        verifyNoMoreInteractions(reportRepository);
    }

    @Test
    @DisplayName("handle(GetReportByIdQuery) should return the Report from repository (AAA)")
    void handle_GetReportByIdQuery_ShouldReturnReport() {
        // Arrange
        ReportId reportId = new ReportId(10L);
        Report expected = mock(Report.class);

        when(reportRepository.findById(eq(reportId))).thenReturn(Optional.of(expected));
        var query = new GetReportByIdQuery(10L);

        // Act
        var actual = reportQueryService.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expected, actual.get());
        verify(reportRepository).findById(reportId);
        verifyNoMoreInteractions(reportRepository);
    }

    @Test
    @DisplayName("handle(GetReportByIdQuery) should return empty Optional when not found (AAA)")
    void handle_GetReportByIdQuery_ShouldReturnEmptyOptionalWhenNotFound() {
        // Arrange
        ReportId reportId = new ReportId(11L);

        when(reportRepository.findById(eq(reportId))).thenReturn(Optional.empty());
        var query = new GetReportByIdQuery(11L);

        // Act
        var actual = reportQueryService.handle(query);

        // Assert
        assertTrue(actual.isEmpty(), "Must return empty Optional.");
        verify(reportRepository).findById(reportId);
        verifyNoMoreInteractions(reportRepository);
    }

    @Test
    @DisplayName("handle(GetReportsByRequestIdQuery) should return list filtered by RequestId (AAA)")
    void handle_GetReportsByRequestIdQuery_ShouldReturnReportsForRequest() {
        // Arrange
        var requestId = new RequestId(12L);
        var a = mock(Report.class);
        var b = mock(Report.class);

        when(reportRepository.findByRequestId(eq(requestId))).thenReturn(List.of(a, b));
        var query = new GetReportsByRequestIdQuery(12L);

        // Act
        var actual = reportQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size(), "Must return a list with 2 Reports.");
        assertSame(a, actual.getFirst());
        verify(reportRepository).findByRequestId(requestId);
        verifyNoMoreInteractions(reportRepository);
    }
}

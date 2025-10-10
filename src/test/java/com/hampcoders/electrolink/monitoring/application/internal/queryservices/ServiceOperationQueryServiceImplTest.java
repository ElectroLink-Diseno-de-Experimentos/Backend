package com.hampcoders.electrolink.monitoring.application.internal.queryservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.queries.*;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ServiceOperationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class ServiceOperationQueryServiceImplTest {
    @Mock
    private ServiceOperationRepository serviceOperationRepository;

    @InjectMocks
    private ServiceOperationQueryServiceImpl serviceOperationQueryService;

    @Test
    @DisplayName("handle(GetAllServiceOperationsQuery) should return the list from repository (AAA)")
    void handle_GetAllServiceOperationsQuery_ShouldReturnAllServiceOperations() {
        // Arrange
        ServiceOperation a = mock(ServiceOperation.class);
        ServiceOperation b = mock(ServiceOperation.class);

        when(serviceOperationRepository.findAll()).thenReturn(List.of(a, b));
        var query = new GetAllServiceOperationsQuery();

        // Act
        var actual = serviceOperationQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size(), "Must return a list with 2 ServiceOperations.");
        assertSame(a, actual.get(0));
        assertSame(b, actual.get(1));
        verify(serviceOperationRepository).findAll();
        verifyNoMoreInteractions(serviceOperationRepository);
    }

    @Test
    @DisplayName("handle(GetServiceOperationByIdQuery) should return the ServiceOperation from repository (AAA)")
    void handle_GetServiceOperationByIdQuery_ShouldReturnServiceOperation() {
        // Arrange
        Long serviceOperationId = 10L;
        ServiceOperation expected = mock(ServiceOperation.class);

        when(serviceOperationRepository.findById(eq(serviceOperationId))).thenReturn(Optional.of(expected));
        var query = new GetServiceOperationByIdQuery(10L);

        // Act
        var actual = serviceOperationQueryService.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expected, actual.get());
        verify(serviceOperationRepository).findById(serviceOperationId);
        verifyNoMoreInteractions(serviceOperationRepository);
    }

    @Test
    @DisplayName("handle(GetServiceOperationByIdQuery) should return empty Optional when not found (AAA)")
    void handle_GetServiceOperationByIdQuery_ShouldReturnEmptyOptionalWhenNotFound() {
        // Arrange
        Long serviceOperationId = 11L;

        when(serviceOperationRepository.findById(eq(serviceOperationId))).thenReturn(Optional.empty());
        var query = new GetServiceOperationByIdQuery(11L);

        // Act
        var actual = serviceOperationQueryService.handle(query);

        // Assert
        assertTrue(actual.isEmpty(), "Must return empty Optional.");
        verify(serviceOperationRepository).findById(serviceOperationId);
        verifyNoMoreInteractions(serviceOperationRepository);
    }

    @Test
    @DisplayName("handle(GetServiceOperationsByTechnicianIdQuery) should return list filtered by TechnicianId (AAA)")
    void handle_GetServiceOperationsByTechnicianIdQuery_ShouldReturnServiceOperationsForRequest() {
        // Arrange
        TechnicianId technicianId = new TechnicianId(12L);
        var a = mock(ServiceOperation.class);
        var b = mock(ServiceOperation.class);

        when(serviceOperationRepository.findByTechnicianId(eq(technicianId))).thenReturn(List.of(a, b));
        var query = new GetServiceOperationsByTechnicianIdQuery(12L);

        // Act
        var actual = serviceOperationQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size(), "Must return a list with 2 Reports.");
        assertSame(a, actual.getFirst());
        verify(serviceOperationRepository).findByTechnicianId(technicianId);
        verifyNoMoreInteractions(serviceOperationRepository);
    }
}

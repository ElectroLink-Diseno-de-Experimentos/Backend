package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.queries.*;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
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

@ExtendWith(MockitoExtension.class)
class ComponentQueryServiceImplTest {

    @Mock
    private ComponentRepository componentRepository;

    @InjectMocks
    private ComponentQueryServiceImpl service;

    private final Long MOCK_UID = 10L;
    private final Long MOCK_TYPE_ID = 2L;

    @Test
    @DisplayName("handle(GetComponentByIdQuery) should return component when found (AAA)")
    void handleGetByIdQuery_ShouldReturnComponent_WhenFound() {
        // Arrange
        var componentIdVO = new ComponentId(MOCK_UID);
        var query = new GetComponentByIdQuery(componentIdVO);
        Component expectedComponent = mock(Component.class);

        when(componentRepository.findById(MOCK_UID)).thenReturn(Optional.of(expectedComponent));

        // Act
        Optional<Component> actual = service.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expectedComponent, actual.get());
        verify(componentRepository).findById(MOCK_UID);
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(GetComponentByIdQuery) should return empty Optional when not found (AAA)")
    void handleGetByIdQuery_ShouldReturnEmptyOptional_WhenNotFound() {
        // Arrange
        var query = new GetComponentByIdQuery(new ComponentId(99L));
        when(componentRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Component> actual = service.handle(query);

        // Assert
        assertTrue(actual.isEmpty());
        verify(componentRepository).findById(99L);
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(GetAllComponentsQuery) should return all components (AAA)")
    void handleGetAllQuery_ShouldReturnAllComponents() {
        // Arrange
        Component a = mock(Component.class);
        Component b = mock(Component.class);
        List<Component> expectedList = List.of(a, b);
        var query = new GetAllComponentsQuery();

        when(componentRepository.findAll()).thenReturn(expectedList);

        // Act
        List<Component> actual = service.handle(query);

        // Assert
        assertEquals(2, actual.size());
        assertSame(a, actual.get(0));
        verify(componentRepository).findAll();
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(GetComponentsByTypeIdQuery) should return components filtered by type ID (AAA)")
    void handleGetByTypeIdQuery_ShouldReturnFilteredComponents() {
        // Arrange
        var query = new GetComponentsByTypeIdQuery(MOCK_TYPE_ID);
        Component a = mock(Component.class);
        List<Component> expectedList = List.of(a);

        when(componentRepository.findByComponentTypeId(MOCK_TYPE_ID)).thenReturn(expectedList);

        // Act
        List<Component> actual = service.handle(query);

        // Assert
        assertEquals(1, actual.size());
        assertSame(a, actual.get(0));
        verify(componentRepository).findByComponentTypeId(MOCK_TYPE_ID);
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(GetComponentsByIdsQuery) should correctly map IDs and return components (AAA)")
    void handleGetByIdsQuery_ShouldReturnMatchingComponents() {
        // Arrange
        List<ComponentId> inputIds = List.of(new ComponentId(1L), new ComponentId(2L));
        List<Long> expectedUids = List.of(1L, 2L);
        var query = new GetComponentsByIdsQuery(inputIds);
        Component a = mock(Component.class);

        when(componentRepository.findByComponentUidIn(expectedUids)).thenReturn(List.of(a));

        // Act
        List<Component> actual = service.handle(query);

        // Assert
        assertEquals(1, actual.size());
        assertSame(a, actual.get(0));
        verify(componentRepository).findByComponentUidIn(expectedUids);
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(GetComponentsByNameQuery) should call repository and apply limit (AAA)")
    void handleGetByNameQuery_ShouldCallRepositoryAndLimitResults() {
        // Arrange
        String searchTerm = "cable";
        int limit = 2;
        var query = new GetComponentsByNameQuery(searchTerm, limit);

        Component a = mock(Component.class);
        Component b = mock(Component.class);
        Component c = mock(Component.class);
        List<Component> mockResults = List.of(a, b, c);

        when(componentRepository.findTop10ByNameContainingIgnoreCase(searchTerm)).thenReturn(mockResults);

        // Act
        List<Component> actual = service.handle(query);

        // Assert
        assertEquals(limit, actual.size(), "Should limit results to 2");
        assertSame(a, actual.get(0));
        assertSame(b, actual.get(1));
        assertFalse(actual.contains(c), "The third element should be filtered by limit()");

        verify(componentRepository).findTop10ByNameContainingIgnoreCase(searchTerm);
        verifyNoMoreInteractions(componentRepository);
    }
}
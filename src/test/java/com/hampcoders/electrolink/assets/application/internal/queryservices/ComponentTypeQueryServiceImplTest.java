package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentTypesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentTypeByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentTypeRepository;
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
class ComponentTypeQueryServiceImplTest {

    @Mock
    private ComponentTypeRepository componentTypeRepository;

    @InjectMocks
    private ComponentTypeQueryServiceImpl service;

    private final Long MOCK_ID = 100L;

    @Test
    @DisplayName("handle(GetComponentTypeByIdQuery) should return component type when found (AAA)")
    void handleGetByIdQuery_ShouldReturnComponentType_WhenFound() {
        // Arrange
        ComponentTypeId typeIdVO = new ComponentTypeId(MOCK_ID);
        var query = new GetComponentTypeByIdQuery(typeIdVO);
        ComponentType expectedType = mock(ComponentType.class);

        when(componentTypeRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedType));

        // Act
        Optional<ComponentType> actual = service.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expectedType, actual.get());
        verify(componentTypeRepository).findById(MOCK_ID);
        verifyNoMoreInteractions(componentTypeRepository);
    }

    @Test
    @DisplayName("handle(GetAllComponentTypesQuery) should return all component types (AAA)")
    void handleGetAllQuery_ShouldReturnAllComponentTypes() {
        // Arrange
        ComponentType a = mock(ComponentType.class);
        ComponentType b = mock(ComponentType.class);
        List<ComponentType> expectedList = List.of(a, b);
        var query = new GetAllComponentTypesQuery();

        when(componentTypeRepository.findAll()).thenReturn(expectedList);

        // Act
        List<ComponentType> actual = service.handle(query);

        // Assert
        assertEquals(2, actual.size());
        assertSame(a, actual.get(0));
        verify(componentTypeRepository).findAll();
        verifyNoMoreInteractions(componentTypeRepository);
    }
}
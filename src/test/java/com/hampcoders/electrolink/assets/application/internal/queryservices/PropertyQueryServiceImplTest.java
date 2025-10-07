package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesByOwnerIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.PropertyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyQueryServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyQueryServiceImpl service;
    
    @Test
    @DisplayName("handle(GetAllPropertiesByOwnerIdQuery) should return properties filtered by owner ID")
    void handleGetByOwnerIdQuery_ShouldReturnFilteredProperties() {
        // Arrange
        Long mockOwnerId = 5L;
        OwnerId mockOwnerIdVO = new OwnerId(mockOwnerId);
        var query = new GetAllPropertiesByOwnerIdQuery(mockOwnerIdVO);
        Property a = mock(Property.class);
        List<Property> expectedList = List.of(a);

        when(propertyRepository.findPropertiesByOwnerId(mockOwnerIdVO)).thenReturn(expectedList);

        // Act
        List<Property> actual = service.handle(query);

        // Assert
        assertEquals(1, actual.size());
        assertSame(a, actual.get(0));
        verify(propertyRepository).findPropertiesByOwnerId(mockOwnerIdVO);
        verifyNoMoreInteractions(propertyRepository);
    }

    @Test
    @DisplayName("handle(GetAllPropertiesQuery) should return all properties")
    void handleGetAllQuery_ShouldReturnAllProperties() {
        // Arrange
        Property a = mock(Property.class);
        Property b = mock(Property.class);
        List<Property> expectedList = List.of(a, b);
        var query = new GetAllPropertiesQuery();

        when(propertyRepository.findAll()).thenReturn(expectedList);

        // Act
        List<Property> actual = service.handle(query);

        // Assert
        assertEquals(2, actual.size());
        assertSame(a, actual.get(0));
        verify(propertyRepository).findAll();
        verifyNoMoreInteractions(propertyRepository);
    }
}
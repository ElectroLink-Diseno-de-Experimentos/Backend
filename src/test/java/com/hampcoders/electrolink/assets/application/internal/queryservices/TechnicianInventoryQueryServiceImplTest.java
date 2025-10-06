package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoriesWithLowStockQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoryByTechnicianIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetStockItemDetailsQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentStockRepository;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.TechnicianInventoryRepository;
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
class TechnicianInventoryQueryServiceImplTest {

    @Mock
    private TechnicianInventoryRepository technicianInventoryRepository;

    @Mock
    private ComponentStockRepository componentStockRepository;

    @InjectMocks
    private TechnicianInventoryQueryServiceImpl service;

    private final Long MOCK_TECHNICIAN_ID = 1L;
    private final Long MOCK_COMPONENT_ID = 20L;
    private final TechnicianId MOCK_TECHNICIAN_VO = new TechnicianId(MOCK_TECHNICIAN_ID);

    @Test
    @DisplayName("handle(GetInventoryByTechnicianIdQuery) should return inventory when found (AAA)")
    void handleGetInventoryByTechnicianIdQuery_ShouldReturnInventory_WhenFound() {
        // Arrange
        var query = new GetInventoryByTechnicianIdQuery(MOCK_TECHNICIAN_VO);
        TechnicianInventory expectedInventory = mock(TechnicianInventory.class);

        when(technicianInventoryRepository.findByTechnicianIdWithStocks(MOCK_TECHNICIAN_ID))
                .thenReturn(Optional.of(expectedInventory));

        // Act
        Optional<TechnicianInventory> actual = service.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expectedInventory, actual.get());
        verify(technicianInventoryRepository).findByTechnicianIdWithStocks(MOCK_TECHNICIAN_ID);
        verifyNoMoreInteractions(technicianInventoryRepository, componentStockRepository);
    }

    @Test
    @DisplayName("handle(GetInventoriesWithLowStockQuery) should return list of inventories (AAA)")
    void handleGetInventoriesWithLowStockQuery_ShouldReturnFilteredInventories() {
        // Arrange
        int threshold = 10;
        var query = new GetInventoriesWithLowStockQuery(threshold);
        TechnicianInventory a = mock(TechnicianInventory.class);
        List<TechnicianInventory> expectedList = List.of(a);

        when(technicianInventoryRepository.findInventoriesWithLowStock(threshold)).thenReturn(expectedList);

        // Act
        List<TechnicianInventory> actual = service.handle(query);

        // Assert
        assertEquals(1, actual.size());
        assertSame(a, actual.get(0));
        verify(technicianInventoryRepository).findInventoriesWithLowStock(threshold);
        verifyNoMoreInteractions(technicianInventoryRepository, componentStockRepository);
    }

    @Test
    @DisplayName("handle(GetStockItemDetailsQuery) should return ComponentStock when found (AAA)")
    void handleGetStockItemDetailsQuery_ShouldReturnStock_WhenFound() {
        // Arrange
        var componentIdVO = new ComponentId(MOCK_COMPONENT_ID);
        var query = new GetStockItemDetailsQuery(MOCK_TECHNICIAN_VO, componentIdVO);
        ComponentStock expectedStock = mock(ComponentStock.class);

        when(componentStockRepository.findByTechnicianInventoryIdAndComponentUid(MOCK_TECHNICIAN_ID, MOCK_COMPONENT_ID))
                .thenReturn(Optional.of(expectedStock));

        // Act
        Optional<ComponentStock> actual = service.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expectedStock, actual.get());
        verify(componentStockRepository).findByTechnicianInventoryIdAndComponentUid(MOCK_TECHNICIAN_ID, MOCK_COMPONENT_ID);
        verifyNoMoreInteractions(technicianInventoryRepository, componentStockRepository);
    }
}
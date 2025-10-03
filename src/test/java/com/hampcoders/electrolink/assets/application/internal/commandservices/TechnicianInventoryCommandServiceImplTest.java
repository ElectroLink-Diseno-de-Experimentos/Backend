package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.commands.AddComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.TechnicianInventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnicianInventoryCommandServiceImplTest {

    @Mock
    private TechnicianInventoryRepository inventoryRepository;

    @Mock
    private ComponentRepository componentRepository;

    @InjectMocks
    private TechnicianInventoryCommandServiceImpl service;

    private final Long TECHNICIAN_ID = 10L;
    private final TechnicianId TECHNICIAN_ID_VO = new TechnicianId(TECHNICIAN_ID);
    private final Long COMPONENT_ID = 20L;

    @Test
    @DisplayName("handle(CreateCommand) should throw IllegalStateException when inventory already exists (AAA)")
    void handleCreateCommand_ShouldThrowException_WhenInventoryAlreadyExists() {
        // ARRANGE
        CreateTechnicianInventoryCommand command = new CreateTechnicianInventoryCommand(TECHNICIAN_ID_VO);
        when(inventoryRepository.existsByTechnicianId(TECHNICIAN_ID)).thenReturn(true);

        // ACT + ASSERT
        assertThrows(IllegalStateException.class, () -> {
            service.handle(command);
        }, "Debe fallar si el inventario para el técnico ya existe.");

        verify(inventoryRepository, times(1)).existsByTechnicianId(TECHNICIAN_ID);
        verify(inventoryRepository, never()).save(any());
        verifyNoInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(CreateCommand) should create and return new inventory UUID when not exists (AAA)")
    void handleCreateCommand_ShouldCreateInventory_WhenNotExists() {
        // ARRANGE
        UUID MOCK_UUID = UUID.randomUUID();
        CreateTechnicianInventoryCommand command = new CreateTechnicianInventoryCommand(TECHNICIAN_ID_VO);

        TechnicianInventory newInventoryMock = mock(TechnicianInventory.class);
        when(newInventoryMock.getId()).thenReturn(MOCK_UUID);

        when(inventoryRepository.existsByTechnicianId(TECHNICIAN_ID)).thenReturn(false);
        when(inventoryRepository.save(any(TechnicianInventory.class))).thenReturn(newInventoryMock);

        // ACT
        UUID resultId = service.handle(command);

        // ASSERT
        assertEquals(MOCK_UUID, resultId);
        verify(inventoryRepository, times(1)).existsByTechnicianId(TECHNICIAN_ID);
        verify(inventoryRepository, times(1)).save(any(TechnicianInventory.class));
        verifyNoInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(AddStockCommand) should throw EntityNotFoundException when inventory not found (AAA)")
    void handleAddStockCommand_ShouldThrowException_WhenInventoryNotFound() {
        // ARRANGE
        AddComponentStockCommand command = new AddComponentStockCommand(TECHNICIAN_ID, COMPONENT_ID, 10, 5);
        when(inventoryRepository.findByTechnicianId(TECHNICIAN_ID)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(EntityNotFoundException.class, () -> {
            service.handle(command);
        }, "Debe fallar si el inventario no se encuentra.");

        verify(inventoryRepository, times(1)).findByTechnicianId(TECHNICIAN_ID);
        verifyNoInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(AddStockCommand) should add stock when inventory and component found (AAA)")
    void handleAddStockCommand_ShouldAddStock_WhenFound() {
        // ARRANGE
        AddComponentStockCommand command = new AddComponentStockCommand(TECHNICIAN_ID, COMPONENT_ID, 10, 5);
        TechnicianInventory mockInventory = mock(TechnicianInventory.class);
        Component mockComponent = mock(Component.class);

        when(inventoryRepository.findByTechnicianId(TECHNICIAN_ID)).thenReturn(Optional.of(mockInventory));
        when(componentRepository.findByComponentUid(COMPONENT_ID)).thenReturn(Optional.of(mockComponent));
        when(inventoryRepository.save(mockInventory)).thenReturn(mockInventory);

        // ACT
        service.handle(command);

        // ASSERT
        verify(inventoryRepository, times(1)).findByTechnicianId(TECHNICIAN_ID);
        verify(componentRepository, times(1)).findByComponentUid(COMPONENT_ID);
        verify(mockInventory, times(1)).addToStock(mockComponent, 10, 5);
        verify(inventoryRepository, times(1)).save(mockInventory);
        verifyNoMoreInteractions(inventoryRepository, componentRepository, mockInventory, mockComponent);
    }

    @Test
    @DisplayName("handle(UpdateStockCommand) should update stock when inventory and stock found (AAA)")
    void handleUpdateStockCommand_ShouldUpdateStock_WhenInventoryAndStockFound() {
        // ARRANGE
        UpdateComponentStockCommand command = new UpdateComponentStockCommand(TECHNICIAN_ID, COMPONENT_ID, 50, 2);

        TechnicianInventory mockInventory = mock(TechnicianInventory.class);
        Component mockComponent = mock(Component.class);
        when(mockComponent.getComponentUid()).thenReturn(COMPONENT_ID);

        ComponentStock mockStock = mock(ComponentStock.class);
        when(mockStock.getComponent()).thenReturn(mockComponent);

        when(inventoryRepository.findByTechnicianId(TECHNICIAN_ID)).thenReturn(Optional.of(mockInventory));
        when(mockInventory.getComponentStocks()).thenReturn(List.of(mockStock));

        // ACT
        service.handle(command);

        // ASSERT
        verify(inventoryRepository, times(1)).findByTechnicianId(TECHNICIAN_ID);
        verify(mockStock, times(1)).updateQuantity(50);
        verify(mockStock, times(1)).updateAlertThreshold(2);
        verify(inventoryRepository, times(1)).save(mockInventory);
        verifyNoMoreInteractions(inventoryRepository, mockInventory, mockStock, mockComponent);
        verifyNoInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(UpdateStockCommand) should throw EntityNotFoundException when stock item not found (AAA)")
    void handleUpdateStockCommand_ShouldThrowException_WhenStockItemNotFound() {
        // ARRANGE
        UpdateComponentStockCommand command = new UpdateComponentStockCommand(TECHNICIAN_ID, 99L, 50, 2);
        TechnicianInventory mockInventory = mock(TechnicianInventory.class);

        when(inventoryRepository.findByTechnicianId(TECHNICIAN_ID)).thenReturn(Optional.of(mockInventory));
        when(mockInventory.getComponentStocks()).thenReturn(List.of());

        // ACT & ASSERT
        assertThrows(EntityNotFoundException.class, () -> {
            service.handle(command);
        }, "Debe fallar si el stock del componente no existe en el inventario.");

        verify(inventoryRepository, times(1)).findByTechnicianId(TECHNICIAN_ID);
        verify(mockInventory, times(1)).getComponentStocks();
        verify(inventoryRepository, never()).save(any());
        verifyNoMoreInteractions(inventoryRepository, mockInventory);
    }

    @Test
    @DisplayName("handle(DeleteStockCommand) should return true when stock item is removed (AAA)")
    void handleDeleteStockCommand_ShouldReturnTrue_WhenStockItemIsRemoved() {
        // ARRANGE
        DeleteComponentStockCommand command = new DeleteComponentStockCommand(TECHNICIAN_ID, COMPONENT_ID);
        TechnicianInventory mockInventory = mock(TechnicianInventory.class);

        when(inventoryRepository.findByTechnicianId(TECHNICIAN_ID)).thenReturn(Optional.of(mockInventory));
        when(mockInventory.removeStockItem(COMPONENT_ID)).thenReturn(true);

        // ACT
        Boolean result = service.handle(command);

        // ASSERT
        assertTrue(result);
        verify(inventoryRepository, times(1)).findByTechnicianId(TECHNICIAN_ID);
        verify(mockInventory, times(1)).removeStockItem(COMPONENT_ID);
        verify(inventoryRepository, times(1)).save(mockInventory);
        verifyNoMoreInteractions(inventoryRepository, mockInventory);
        verifyNoInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(DeleteStockCommand) should return false when inventory not found (AAA)")
    void handleDeleteStockCommand_ShouldReturnFalse_WhenInventoryNotFound() {
        // ARRANGE
        DeleteComponentStockCommand command = new DeleteComponentStockCommand(99L, COMPONENT_ID);
        when(inventoryRepository.findByTechnicianId(99L)).thenReturn(Optional.empty());

        // ACT
        Boolean result = service.handle(command);

        // ASSERT
        assertFalse(result);
        verify(inventoryRepository, times(1)).findByTechnicianId(99L);
        verify(inventoryRepository, never()).save(any());
        verifyNoMoreInteractions(inventoryRepository);
        verifyNoInteractions(componentRepository);
    }
}
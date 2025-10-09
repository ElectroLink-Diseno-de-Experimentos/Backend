package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComponentCommandServiceImplTest {

    @Mock
    private ComponentRepository componentRepository;

    @InjectMocks
    private ComponentCommandServiceImpl service;

    @Test
    @DisplayName("handle(CreateComponentCommand) should create component when name is unique (AAA)")
    void handleCreateCommand_ShouldCreateComponent_WhenNameIsUnique() {
        // ARRANGE
        Long MOCK_UID = 99L;
        CreateComponentCommand command = new CreateComponentCommand(UUID.randomUUID(), "TestComponent", "Desc", 1L, true);
        Component newComponentMock = mock(Component.class);

        when(componentRepository.existsByName(command.name())).thenReturn(false);
        when(newComponentMock.getComponentUid()).thenReturn(MOCK_UID);
        when(componentRepository.save(any(Component.class))).thenReturn(newComponentMock);

        // ACT
        ComponentId resultId = service.handle(command);

        // ASSERT
        assertNotNull(resultId);
        assertEquals(MOCK_UID, resultId.componentId());
        verify(componentRepository, times(1)).save(any(Component.class));
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(CreateComponentCommand) should throw IllegalStateException when name exists (AAA)")
    void handleCreateCommand_ShouldThrowException_WhenNameExists() {
        // ARRANGE
        CreateComponentCommand command = new CreateComponentCommand(UUID.randomUUID(), "DuplicateName", "Desc", 1L, true);

        when(componentRepository.existsByName(command.name())).thenReturn(true);

        // ACT & ASSERT
        assertThrows(IllegalStateException.class, () -> {
            service.handle(command);
        }, "Debe lanzar IllegalStateException si el nombre ya existe.");

        // ASSERT
        verify(componentRepository, times(1)).existsByName(command.name());
        verify(componentRepository, never()).save(any());
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(UpdateComponentCommand) should update component and return it when found (AAA)")
    void handleUpdateCommand_ShouldUpdateComponent_WhenFound() {
        // ARRANGE
        Long existingId = 1L;
        String newName = "Updated Name";
        String newDesc = "Updated Desc";
        UpdateComponentCommand command = new UpdateComponentCommand(existingId, newName, newDesc, 2L, false);

        Component mockComponent = mock(Component.class);
        when(componentRepository.findById(existingId)).thenReturn(Optional.of(mockComponent));
        when(componentRepository.save(mockComponent)).thenReturn(mockComponent);

        // ACT
        Optional<Component> result = service.handle(command);

        // ASSERT
        assertTrue(result.isPresent());
        verify(componentRepository, times(1)).findById(existingId);
        verify(mockComponent, times(1)).updateInfo(newName, newDesc);
        verify(componentRepository, times(1)).save(mockComponent);
        verifyNoMoreInteractions(componentRepository, mockComponent);
    }

    @Test
    @DisplayName("handle(DeleteComponentCommand) should return true when component is deleted (AAA)")
    void handleDeleteCommand_ShouldReturnTrue_WhenComponentIsDeleted() {
        // ARRANGE
        Long componentId = 5L;
        DeleteComponentCommand command = new DeleteComponentCommand(componentId);
        when(componentRepository.existsById(componentId)).thenReturn(true);

        // ACT
        Boolean result = service.handle(command);

        // ASSERT
        assertTrue(result);
        verify(componentRepository, times(1)).existsById(componentId);
        verify(componentRepository, times(1)).deleteById(componentId);
        verifyNoMoreInteractions(componentRepository);
    }

    @Test
    @DisplayName("handle(DeleteComponentCommand) should return false when component does not exist (AAA)")
    void handleDeleteCommand_ShouldReturnFalse_WhenComponentDoesNotExist() {
        // ARRANGE
        Long componentId = 99L;
        DeleteComponentCommand command = new DeleteComponentCommand(componentId);
        when(componentRepository.existsById(componentId)).thenReturn(false);

        // ACT
        Boolean result = service.handle(command);

        // ASSERT
        assertFalse(result);
        verify(componentRepository, times(1)).existsById(componentId);
        verify(componentRepository, never()).deleteById(any());
        verifyNoMoreInteractions(componentRepository);
    }
}
package com.hampcoders.electrolink.profiles.application.internal.commandservices;

import com.hampcoders.electrolink.profiles.domain.model.aggregates.Profile;
import com.hampcoders.electrolink.profiles.domain.model.commands.CreateProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.commands.DeleteProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.commands.UpdateProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;
import com.hampcoders.electrolink.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.hampcoders.electrolink.profiles.application.internal.outboundservices.ExternalAssetsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileCommandServiceImplTest {
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private ExternalAssetsService externalAssetsService;
    @InjectMocks
    private ProfileCommandServiceImpl service;

    // ========== CreateProfileCommand ==========
    @Test
    void handleCreateProfile_Success_HomeOwner() {
        // Arrange
        CreateProfileCommand command = new CreateProfileCommand(
                "Juan", "Pérez", "juan@example.com", "Calle 1", Role.HOMEOWNER, "InfoHomeOwner"
        );
        when(profileRepository.existsByEmail_Address(command.email())).thenReturn(false);
        Profile profile = new Profile(
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.PersonName("Juan", "Pérez"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.EmailAddress("juan@example.com"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.StreetAddress("Calle 1"),
            Role.HOMEOWNER
        );
        // Asignar ID por reflexión
        try {
            Method setId = profile.getClass().getSuperclass().getDeclaredMethod("setId", Long.class);
            setId.setAccessible(true);
            setId.invoke(profile, 1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Mock para que el save devuelva el perfil con ID asignado
        when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> {
            Profile p = invocation.getArgument(0);
            try {
                Method setId = p.getClass().getSuperclass().getDeclaredMethod("setId", Long.class);
                setId.setAccessible(true);
                setId.invoke(p, 1L);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return p;
        });
        // Act
        Long id = service.handle(command);
        // Assert
        assertNotNull(id);
        assertEquals(1L, id);
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    void handleCreateProfile_Success_Technician() {
        // Arrange
        CreateProfileCommand command = new CreateProfileCommand(
                "Ana", "García", "ana@example.com", "Calle 2", Role.TECHNICIAN, "InfoTech"
        );
        when(profileRepository.existsByEmail_Address(command.email())).thenReturn(false);
        Profile profile = new Profile(
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.PersonName("Ana", "García"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.EmailAddress("ana@example.com"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.StreetAddress("Calle 2"),
            Role.TECHNICIAN
        );
        try {
            Method setId = profile.getClass().getSuperclass().getDeclaredMethod("setId", Long.class);
            setId.setAccessible(true);
            setId.invoke(profile, 2L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Mock para que el save devuelva el perfil con ID asignado
        when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> {
            Profile p = invocation.getArgument(0);
            try {
                Method setId = p.getClass().getSuperclass().getDeclaredMethod("setId", Long.class);
                setId.setAccessible(true);
                setId.invoke(p, 2L);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return p;
        });
        // Act
        Long id = service.handle(command);
        // Assert
        assertNotNull(id);
        assertEquals(2L, id);
        verify(profileRepository).save(any(Profile.class));
        verify(externalAssetsService).createInventoryForTechnician(2L);
    }

    @Test
    void handleCreateProfile_EmailExists_ShouldThrow() {
        // Arrange
        CreateProfileCommand command = new CreateProfileCommand(
                "Juan", "Pérez", "juan@example.com", "Calle 1", Role.HOMEOWNER, "InfoHomeOwner"
        );
        when(profileRepository.existsByEmail_Address(command.email())).thenReturn(true);
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.handle(command));
        assertTrue(ex.getMessage().contains("already exists"));
    }

    @Test
    void handleCreateProfile_SaveError_ShouldThrow() {
        // Arrange
        CreateProfileCommand command = new CreateProfileCommand(
                "Juan", "Pérez", "juan@example.com", "Calle 1", Role.HOMEOWNER, "InfoHomeOwner"
        );
        when(profileRepository.existsByEmail_Address(command.email())).thenReturn(false);
        when(profileRepository.save(any(Profile.class))).thenThrow(new RuntimeException("DB error"));
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.handle(command));
        assertTrue(ex.getMessage().contains("Error while saving profile"));
    }

    // ========== UpdateProfileCommand ==========
    @Test
    void handleUpdateProfile_Success() {
        // Arrange
        UpdateProfileCommand command = new UpdateProfileCommand(
                1L, "Juan", "Pérez", "juan@example.com", "Calle 1", Role.HOMEOWNER, "InfoHomeOwner"
        );
        Profile profile = new Profile(
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.PersonName("Juan", "Pérez"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.EmailAddress("juan@example.com"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.StreetAddress("Calle 1"),
            Role.HOMEOWNER
        );
        try {
            Method setId = profile.getClass().getSuperclass().getDeclaredMethod("setId", Long.class);
            setId.setAccessible(true);
            setId.invoke(profile, 1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        when(profileRepository.existsById(1L)).thenReturn(true);
        when(profileRepository.existsByEmail_AddressAndIdIsNot(command.email(), 1L)).thenReturn(false);
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenReturn(profile);
        // Act
        Optional<Profile> result = service.handle(command);
        // Assert
        assertTrue(result.isPresent());
        verify(profileRepository).save(profile);
    }

    @Test
    void handleUpdateProfile_ProfileNotFound_ShouldThrow() {
        // Arrange
        UpdateProfileCommand command = new UpdateProfileCommand(
                99L, "Juan", "Pérez", "juan@example.com", "Calle 1", Role.HOMEOWNER, "InfoHomeOwner"
        );
        when(profileRepository.existsById(99L)).thenReturn(false);
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.handle(command));
        assertTrue(ex.getMessage().contains("does not exist"));
    }

    @Test
    void handleUpdateProfile_EmailUsedByOther_ShouldThrow() {
        // Arrange
        UpdateProfileCommand command = new UpdateProfileCommand(
                1L, "Juan", "Pérez", "juan@example.com", "Calle 1", Role.HOMEOWNER, "InfoHomeOwner"
        );
        when(profileRepository.existsById(1L)).thenReturn(true);
        when(profileRepository.existsByEmail_AddressAndIdIsNot(command.email(), 1L)).thenReturn(true);
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.handle(command));
        assertTrue(ex.getMessage().contains("already used"));
    }

    @Test
    void handleUpdateProfile_SaveError_ShouldThrow() {
        // Arrange
        UpdateProfileCommand command = new UpdateProfileCommand(
                1L, "Juan", "Pérez", "juan@example.com", "Calle 1", Role.HOMEOWNER, "InfoHomeOwner"
        );
        Profile profile = new Profile(
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.PersonName("Juan", "Pérez"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.EmailAddress("juan@example.com"),
            new com.hampcoders.electrolink.profiles.domain.model.valueobjects.StreetAddress("Calle 1"),
            Role.HOMEOWNER
        );
        try {
            Method setId = profile.getClass().getSuperclass().getDeclaredMethod("setId", Long.class);
            setId.setAccessible(true);
            setId.invoke(profile, 1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        when(profileRepository.existsById(1L)).thenReturn(true);
        when(profileRepository.existsByEmail_AddressAndIdIsNot(command.email(), 1L)).thenReturn(false);
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenThrow(new RuntimeException("DB error"));
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.handle(command));
        assertTrue(ex.getMessage().contains("Error while updating profile"));
    }

    // ========== DeleteProfileCommand ==========
    @Test
    void handleDeleteProfile_Success() {
        // Arrange
        DeleteProfileCommand command = new DeleteProfileCommand(1L);
        when(profileRepository.existsById(1L)).thenReturn(true);
        doNothing().when(profileRepository).deleteById(1L);
        // Act
        service.handle(command);
        // Assert
        verify(profileRepository).deleteById(1L);
    }

    @Test
    void handleDeleteProfile_ProfileNotFound_ShouldThrow() {
        // Arrange
        DeleteProfileCommand command = new DeleteProfileCommand(99L);
        when(profileRepository.existsById(99L)).thenReturn(false);
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.handle(command));
        assertTrue(ex.getMessage().contains("does not exist"));
    }

    @Test
    void handleDeleteProfile_DeleteError_ShouldThrow() {
        // Arrange
        DeleteProfileCommand command = new DeleteProfileCommand(1L);
        when(profileRepository.existsById(1L)).thenReturn(true);
        doThrow(new RuntimeException("DB error")).when(profileRepository).deleteById(1L);
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.handle(command));
        assertTrue(ex.getMessage().contains("Error while deleting profile"));
    }
}

package com.hampcoders.electrolink.profiles.domain.model.commands;

import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateProfileCommandTest {
    @Test
    void shouldCreateProfileCommandWithCorrectValues() {
        // Arrange
        String firstName = "Juan";
        String lastName = "Pérez";
        String email = "juan.perez@example.com";
        String street = "Calle 123";
        Role role = Role.HOMEOWNER;
        String additionalInfo = "Certificado A";

        // Act
        CreateProfileCommand command = new CreateProfileCommand(
                firstName, lastName, email, street, role, additionalInfo
        );

        // Assert
        assertEquals(firstName, command.firstName());
        assertEquals(lastName, command.lastName());
        assertEquals(email, command.email());
        assertEquals(street, command.street());
        assertEquals(role, command.role());
        assertEquals(additionalInfo, command.additionalInfoOrCertification());
    }
}

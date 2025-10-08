package com.hampcoders.electrolink.profiles.domain.model.commands;

import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateProfileCommandTest {
    @Test
    void shouldCreateUpdateProfileCommandWithCorrectValues() {
        // Arrange
        Long profileId = 456L;
        String firstName = "Ana";
        String lastName = "García";
        String email = "ana.garcia@example.com";
        String street = "Avenida 456";
        Role role = Role.TECHNICIAN;
        String additionalInfo = "Certificado B";

        // Act
        UpdateProfileCommand command = new UpdateProfileCommand(
                profileId, firstName, lastName, email, street, role, additionalInfo
        );

        // Assert
        assertEquals(profileId, command.profileId());
        assertEquals(firstName, command.firstName());
        assertEquals(lastName, command.lastName());
        assertEquals(email, command.email());
        assertEquals(street, command.street());
        assertEquals(role, command.role());
        assertEquals(additionalInfo, command.additionalInfoOrCertification());
    }
}

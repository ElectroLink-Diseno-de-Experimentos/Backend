package com.hampcoders.electrolink.profiles.domain.model.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeleteProfileCommandTest {
    @Test
    void shouldCreateDeleteProfileCommandWithCorrectId() {
        // Arrange
        Long profileId = 123L;

        // Act
        DeleteProfileCommand command = new DeleteProfileCommand(profileId);

        // Assert
        assertEquals(profileId, command.profileId());
    }
}


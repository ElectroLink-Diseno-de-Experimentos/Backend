package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateRatingCommand;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateRatingResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateRatingCommandFromResourceAssemblerTest {
    @Test
    @DisplayName("toCommandFromResource should correctly map UpdateRatingResource to UpdateRatingCommand (AAA)")
    void toCommandFromResource_ShouldMapResourceToCommand() {
        // Arrange
        Long ratingId = 55L;
        int score = 3;
        String comment = "Needs immediate follow-up.";

        var resource = new UpdateRatingResource(ratingId, score, comment);

        // Act
        UpdateRatingCommand command = UpdateRatingCommandFromResourceAssembler.toCommandFromResource(resource);

        // Assert
        assertNotNull(command, "El comando retornado no debe ser nulo.");

        assertEquals(ratingId, command.ratingId(),
                "El RatingId debe ser mapeado y envuelto en un Value Object.");
        assertEquals(score, command.score(), "El score de actualización debe coincidir.");
        assertEquals(comment, command.comment(), "El comentario de actualización debe coincidir.");
    }
}

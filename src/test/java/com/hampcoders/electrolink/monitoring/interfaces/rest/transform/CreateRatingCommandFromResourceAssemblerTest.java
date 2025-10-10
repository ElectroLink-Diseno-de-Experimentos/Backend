package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateRatingResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateRatingCommandFromResourceAssemblerTest {
    @Test
    @DisplayName("toCommandFromResource should correctly map CreateRatingResource to AddRatingCommand (AAA)")
    void toCommandFromResource_ShouldMapResourceToCommand() {
        // Arrange
        Long requestId = 10L;
        Integer score = 5;
        String comment = "Some comment.";
        String raterId = "1";
        Long technicianId = 11L;

        var resource = new CreateRatingResource(requestId, score, comment, raterId, technicianId);

        // Act
        var command = CreateRatingCommandFromResourceAssembler.toCommandFromResource(resource);

        // Assert
        assertNotNull(command, "El comando retornado no debe ser nulo.");
        assertEquals(new RequestId(requestId), command.requestId(),
                "El RequestId debe ser mapeado y envuelto en un Value Object.");
        assertEquals(new TechnicianId(technicianId), command.technicianId(),
                "El TechnicianId debe ser mapeado y envuelto en un Value Object.");
        assertEquals(score, command.score(), "El score debe coincidir.");
        assertEquals(comment, command.comment(), "El comentario debe coincidir.");
        assertEquals(raterId, command.raterId(), "El raterId debe coincidir.");
    }
}

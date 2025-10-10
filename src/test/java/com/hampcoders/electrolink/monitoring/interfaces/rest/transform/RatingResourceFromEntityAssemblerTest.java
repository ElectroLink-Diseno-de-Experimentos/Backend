package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RatingResourceFromEntityAssemblerTest {
    @Test
    @DisplayName("toResourceFromEntity should correctly map Rating entity to RatingResource (AAA)")
    void toResourceFromEntity_ShouldMapEntityToResource() {
        // Arrange
        Long ratingId = 50L;
        Long SAMPLE_REQUEST_ID_LONG = 100L;
        Long SAMPLE_TECHNICIAN_ID_LONG = 200L;
        int score = 4;
        String comment = "Good job, but needs improvement in response time.";
        String raterId = "1";

        var requestId = new RequestId(SAMPLE_REQUEST_ID_LONG);
        var technicianId = new TechnicianId(SAMPLE_TECHNICIAN_ID_LONG);

        Rating entity = mock(Rating.class);
        when(entity.getId()).thenReturn(ratingId);
        when(entity.getRequestId()).thenReturn(requestId);
        when(entity.getScore()).thenReturn(score);
        when(entity.getComment()).thenReturn(comment);
        when(entity.getRaterId()).thenReturn(raterId);
        when(entity.getTechnicianId()).thenReturn(technicianId);

        // Act
        var resource = RatingResourceFromEntityAssembler.toResourceFromEntity(entity);

        // Assert
        assertNotNull(resource, "El recurso retornado no debe ser nulo.");

        assertEquals(ratingId, resource.id(), "El ID del Rating debe coincidir.");
        assertEquals(SAMPLE_REQUEST_ID_LONG, resource.requestId(), "El RequestId (ID Long) debe coincidir.");
        assertEquals(score, resource.score(), "El score debe coincidir.");
        assertEquals(comment, resource.comment(), "El comentario debe coincidir.");
        assertEquals(raterId, resource.raterId(), "El raterId debe coincidir.");
        assertEquals(SAMPLE_TECHNICIAN_ID_LONG, resource.technicianId(), "El TechnicianId (ID Long) debe coincidir.");

        verify(entity).getId();
        verify(entity).getRequestId();
        verify(entity).getScore();
        verify(entity).getComment();
        verify(entity).getRaterId();
        verify(entity).getTechnicianId();
        verifyNoMoreInteractions(entity);
    }
}

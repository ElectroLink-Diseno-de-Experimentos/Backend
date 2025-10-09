package com.hampcoders.electrolink.monitoring.application.internal.commandservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.RatingRepository;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ServiceOperationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingCommandServiceImplTest {
    @Mock
    private RatingRepository ratingRepository;
    @Mock
    private ServiceOperationRepository serviceOperationRepository;

    @InjectMocks
    private RatingCommandServiceImpl ratingCommandService;


    // -------------------------------------------------------------------------
    // handle(AddRatingCommand command) - CREATE
    // -------------------------------------------------------------------------
    @Test
    @DisplayName("handle(AddRatingCommand) should save Rating if ServiceOperation is COMPLETED (AAA)")
    void handle_AddRatingCommand_ShouldSaveRating_WhenServiceCompleted() {
        // Arrange
        RequestId requestId = new RequestId(10L);
        TechnicianId technicianId = new TechnicianId(11L);
        ServiceOperation serviceOperation = mock(ServiceOperation.class);

        when(serviceOperationRepository.findByRequestId(eq(requestId))).thenReturn(Optional.of(serviceOperation));
        when(serviceOperation.getStatus()).thenReturn(ServiceStatus.COMPLETED);
        when(ratingRepository.save(any(Rating.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var command = new AddRatingCommand(requestId, 5, "Excellent", "1", technicianId);

        // Act
        Long id = ratingCommandService.handle(command);

        // Assert
        assertNull(id, "Expected id to be null because repository save is mocked and no id is set");

        verify(serviceOperationRepository, times(1)).findByRequestId(requestId);
        verify(serviceOperation, times(1)).getStatus();
        verify(ratingRepository, times(1)).save(any(Rating.class));

        verifyNoMoreInteractions(serviceOperationRepository, ratingRepository);
    }

    @Test
    @DisplayName("handle(AddRatingCommand) should throw IllegalStateException if ServiceOperation is not COMPLETED (AAA)")
    void handle_AddRatingCommand_ShouldThrowException_WhenServiceIsNotCompleted() {
        // Arrange
        RequestId requestId = new RequestId(10L);
        TechnicianId technicianId = new TechnicianId(11L);

        var serviceOperation = mock(ServiceOperation.class);
        when(serviceOperationRepository.findByRequestId(eq(requestId))).thenReturn(Optional.of(serviceOperation));
        when(serviceOperation.getStatus()).thenReturn(ServiceStatus.PENDING);

        var command = new AddRatingCommand(requestId, 5, "Pretty Good", "2", technicianId);

        // Act + Assert
        var ex = assertThrows(IllegalStateException.class, () -> ratingCommandService.handle(command));

        assertTrue(ex.getMessage().contains("Cannot add rating: associated ServiceOperation is not completed."));
        verify(serviceOperationRepository, times(1)).findByRequestId(requestId);
        verify(serviceOperation, times(1)).getStatus();
        verify(ratingRepository, never()).save(any(Rating.class));
        verifyNoMoreInteractions(serviceOperationRepository);
        verifyNoInteractions(ratingRepository);
    }

    @Test
    @DisplayName("handle(AddRatingCommand) should throw IllegalArgumentException if ServiceOperation not found (AAA)")
    void handle_AddRatingCommand_ShouldThrowException_WhenServiceNotFound(){
        // Arrange
        RequestId requestId = new RequestId(10L);
        TechnicianId technicianId = new TechnicianId(11L);

        when(serviceOperationRepository.findByRequestId(eq(requestId))).thenReturn(Optional.empty());

        var command = new AddRatingCommand(requestId, 5, "Not found test", "3", technicianId);

        // Act + Assert
        var ex = assertThrows(IllegalArgumentException.class, () -> {
            ratingCommandService.handle(command);
        }, "Debe lanzar IllegalArgumentException si no existe ServiceOperation.");

        assertTrue(ex.getMessage().contains("No ServiceOperation found for the given RequestId"));
        verify(serviceOperationRepository, times(1)).findByRequestId(requestId);
        verify(ratingRepository, never()).save(any(Rating.class));
        verifyNoMoreInteractions(serviceOperationRepository);
        verifyNoInteractions(ratingRepository);
    }

    // -------------------------------------------------------------------------
    // handle(UpdateRatingCommand command) - UPDATE
    // -------------------------------------------------------------------------
    @Test
    @DisplayName("handle(UpdateRatingCommand) should update score, comment and save (AAA)")
    void handle_UpdateRatingCommand_ShouldUpdateAndSave(){
        // Arrange
        Long ratingId = 10L;
        var newScore = 5;
        var newComment = "Updated comment.";
        var existingRating = mock(Rating.class);

        when(ratingRepository.findById(eq(ratingId))).thenReturn(Optional.of(existingRating));

        var command = new UpdateRatingCommand(ratingId, newScore, newComment);

        // Act
        ratingCommandService.handle(command);

        // Assert
        verify(ratingRepository, times(1)).findById(ratingId);
        verify(existingRating, times(1)).updateScore(newScore);
        verify(existingRating, times(1)).updateComment(newComment);
        verify(ratingRepository, times(1)).save(existingRating);

        verifyNoMoreInteractions(ratingRepository);
        verifyNoInteractions(serviceOperationRepository);
    }

    @Test
    @DisplayName("handle(UpdateRatingCommand) should throw IllegalArgumentException if Rating not found (AAA)")
    void handle_UpdateRatingCommand_ShouldThrowException_WhenNotFound(){
        // Arrange
        Long ratingId = 10L;
        when(ratingRepository.findById(eq(ratingId))).thenReturn(Optional.empty());

        var command = new UpdateRatingCommand(ratingId, 5, "Updated comment.");

        // Act + Assert
        var ex = assertThrows(IllegalArgumentException.class, () -> {
            ratingCommandService.handle(command);
        }, "Debe lanzar IllegalArgumentException si no existe Rating.");

        assertTrue(ex.getMessage().contains("Rating not found"));

        verify(ratingRepository, times(1)).findById(ratingId);
        verify(ratingRepository, never()).save(any(Rating.class));
        verifyNoMoreInteractions(ratingRepository);
        verifyNoInteractions(serviceOperationRepository);
    }

    // -------------------------------------------------------------------------
    // handle(DeleteRatingCommand command) - DELETE
    // -------------------------------------------------------------------------
    @Test
    @DisplayName("handle(DeleteRatingCommand) should delete Rating when found (AAA)")
    void handle_DeleteRatingCommand_ShouldDeleteRating(){
        // Arrange
        Long ratingId = 10L;
        var existingRating = mock(Rating.class);

        when(ratingRepository.findById(eq(ratingId))).thenReturn(Optional.of(existingRating));
        var command = new DeleteRatingCommand(ratingId);

        // Act
        ratingCommandService.handle(command);

        // Assert
        verify(ratingRepository, times(1)).findById(ratingId);
        verify(ratingRepository, times(1)).delete(existingRating);

        verifyNoMoreInteractions(ratingRepository);
        verifyNoInteractions(serviceOperationRepository);
    }

    @Test
    @DisplayName("handle(DeleteRatingCommand) should throw IllegalArgumentException if Rating not found (AAA)")
    void handle_DeleteRatingCommand_ShouldThrowException_WhenNotFound(){
        // Arrange
        Long ratingId = 10L;
        when(ratingRepository.findById(eq(ratingId))).thenReturn(Optional.empty());
        var command = new DeleteRatingCommand(ratingId);

        // Act + Assert
        var ex = assertThrows(IllegalArgumentException.class, () -> {
            ratingCommandService.handle(new DeleteRatingCommand(10L));
        }, "Debe lanzar IllegalArgumentException si no existe Rating.");

        assertTrue(ex.getMessage().contains("Rating not found"));

        verify(ratingRepository, times(1)).findById(ratingId);
        verify(ratingRepository, never()).delete(any(Rating.class));
        verifyNoMoreInteractions(ratingRepository);
        verifyNoInteractions(serviceOperationRepository);
    }
}

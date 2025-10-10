package com.hampcoders.electrolink.monitoring.application.internal.queryservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllRatingsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByRequestIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByTechnicianIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.RatingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class RatingQueryServiceImplTest {
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingQueryServiceImpl ratingQueryService;

    @Test
    @DisplayName("handle(GetAllRatingsQuery) should return the list from repository (AAA)")
    void handle_GetAllRatingsQuery_ShouldReturnAllRatings() {
        // Arrange
        Rating a = mock(Rating.class);
        Rating b = mock(Rating.class);

        when(ratingRepository.findAll()).thenReturn(List.of(a, b));
        var query = new GetAllRatingsQuery();

        // Act
        var actual = ratingQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size(), "Must return a list with 2 Ratings.");
        assertSame(a, actual.get(0));
        assertSame(b, actual.get(1));
        verify(ratingRepository).findAll();
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    @DisplayName("handle(GetRatingByIdQuery) should return the Rating from repository (AAA)")
    void handle_GetRatingByIdQuery_ShouldReturnRating() {
        // Arrange
        Long ratingId = 10L;
        Rating expected = mock(Rating.class);

        when(ratingRepository.findById(eq(ratingId))).thenReturn(Optional.of(expected));
        var query = new GetRatingByIdQuery(10L);

        // Act
        var actual = ratingQueryService.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expected, actual.get());
        verify(ratingRepository).findById(ratingId);
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    @DisplayName("handle(GetRatingByIdQuery) should return empty Optional when not found (AAA)")
    void handle_GetRatingByIdQuery_ShouldReturnEmptyOptionalWhenNotFound() {
        // Arrange
        Long ratingId = 11L;

        when(ratingRepository.findById(eq(ratingId))).thenReturn(Optional.empty());
        var query = new GetRatingByIdQuery(11L);

        // Act
        var actual = ratingQueryService.handle(query);

        // Assert
        assertTrue(actual.isEmpty(), "Must return empty Optional.");
        verify(ratingRepository).findById(ratingId);
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    @DisplayName("handle(GetRatingsByRequestIdQuery) should return list filtered by RequestId (AAA)")
    void handle_GetRatingsByRequestIdQuery_ShouldReturnRatingsForRequest() {
        // Arrange
        var requestId = new RequestId(12L);
        var a = mock(Rating.class);
        var b = mock(Rating.class);

        when(ratingRepository.findByRequestId(eq(requestId))).thenReturn(List.of(a, b));
        var query = new GetRatingsByRequestIdQuery(12L);

        // Act
        var actual = ratingQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size(), "Must return a list with 2 Ratings.");
        assertSame(a, actual.getFirst());
        verify(ratingRepository).findByRequestId(requestId);
        verifyNoMoreInteractions(ratingRepository);
    }

    @Test
    @DisplayName("handle(GetRatingsByTechnicianIdQuery) should return list filtered by TechnicianId (AAA)")
    void handle_GetRatingsByTechnicianIdQuery_ShouldReturnRatingsForTechnician() {
        // Arrange
        var expectedTechnicianId = new TechnicianId(13L);
        var a = mock(Rating.class);
        var b = mock(Rating.class);

        when(ratingRepository.findByTechnicianId(eq(expectedTechnicianId))).thenReturn(List.of(a, b));
        var query = new GetRatingsByTechnicianIdQuery(13L);

        // Act
        var actual = ratingQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size(), "Must return a list with 2 Ratings.");
        assertSame(b, actual.get(1));
        verify(ratingRepository).findByTechnicianId(expectedTechnicianId);
        verifyNoMoreInteractions(ratingRepository);
    }
}

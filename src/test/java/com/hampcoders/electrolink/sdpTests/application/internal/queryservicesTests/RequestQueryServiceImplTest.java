package com.hampcoders.electrolink.sdpTests.application.internal.queryservicesTests;

import com.hampcoders.electrolink.sdp.application.internal.queryservices.RequestQueryServiceImpl;
import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestsByClientIdQuery;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.RequestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestQueryServiceImplTest {

  @Mock
  private RequestRepository requestRepository;

  @InjectMocks
  private RequestQueryServiceImpl service;

  // ========== Tests for FindRequestByIdQuery ==========

  @Test
  @DisplayName("handle(FindRequestByIdQuery) should return Optional with Request when it exists")
  void handle_FindById_WhenRequestExists_ShouldReturnOptionalWithRequest(){
    // Arrange
    Long requestId = 10L;
    var query = new FindRequestByIdQuery(requestId);
    Request expected = mock(Request.class);
    when(requestRepository.findById(requestId)).thenReturn(Optional.of(expected));

    // Act
    Optional<Request> actual = service.handle(query);

    // Assert
    assertTrue(actual.isPresent(), "Optional should contain a Request");
    assertSame(expected, actual.get(), "Should return the same Request instance");
    verify(requestRepository, times(1)).findById(requestId);
    verifyNoMoreInteractions(requestRepository);
  }

  @Test
  @DisplayName("handle(FindRequestByIdQuery) should return empty Optional when Request does not exist")
  void handle_FindById_WhenRequestDoesNotExist_ShouldReturnEmptyOptional(){
    // Arrange
    Long requestId = 999L;
    var query = new FindRequestByIdQuery(requestId);
    when(requestRepository.findById(requestId)).thenReturn(Optional.empty());

    // Act
    Optional<Request> actual = service.handle(query);

    // Assert
    assertFalse(actual.isPresent(), "Optional should be empty when Request does not exist");
    verify(requestRepository, times(1)).findById(requestId);
    verifyNoMoreInteractions(requestRepository);
  }

  // ========== Tests for FindRequestsByClientIdQuery ==========

  @Test
  @DisplayName("handle(FindRequestsByClientIdQuery) should return List with multiple Requests when they exist")
  void handle_FindByClientId_WhenMultipleRequestsExist_ShouldReturnList() {
    // Arrange
    String clientId = "CLIENT-123";
    Request requestA = mock(Request.class);
    Request requestB = mock(Request.class);
    when(requestRepository.findByClientId(clientId)).thenReturn(List.of(requestA, requestB));
    var query = new FindRequestsByClientIdQuery(clientId);

    // Act
    List<Request> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertEquals(2, actual.size(), "Should return 2 requests");
    assertSame(requestA, actual.get(0), "First request should match");
    assertSame(requestB, actual.get(1), "Second request should match");
    verify(requestRepository, times(1)).findByClientId(clientId);
    verifyNoMoreInteractions(requestRepository);
  }

  @Test
  @DisplayName("handle(FindRequestsByClientIdQuery) should return empty List when no Requests exist for client")
  void handle_FindByClientId_WhenNoRequestsExist_ShouldReturnEmptyList() {
    // Arrange
    String clientId = "CLIENT-999";
    when(requestRepository.findByClientId(clientId)).thenReturn(Collections.emptyList());
    var query = new FindRequestsByClientIdQuery(clientId);

    // Act
    List<Request> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertTrue(actual.isEmpty(), "List should be empty when no requests exist");
    assertEquals(0, actual.size(), "Size should be 0");
    verify(requestRepository, times(1)).findByClientId(clientId);
    verifyNoMoreInteractions(requestRepository);
  }

  @Test
  @DisplayName("handle(FindRequestsByClientIdQuery) should return List with single Request when only one exists")
  void handle_FindByClientId_WhenSingleRequestExists_ShouldReturnListWithOne() {
    // Arrange
    String clientId = "CLIENT-456";
    Request request = mock(Request.class);
    when(requestRepository.findByClientId(clientId)).thenReturn(List.of(request));
    var query = new FindRequestsByClientIdQuery(clientId);

    // Act
    List<Request> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertEquals(1, actual.size(), "Should return exactly 1 request");
    assertSame(request, actual.get(0), "Request should match");
    verify(requestRepository, times(1)).findByClientId(clientId);
    verifyNoMoreInteractions(requestRepository);
  }
}
